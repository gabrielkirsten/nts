package com.nts.campaignservice.service;

import com.nts.campaignservice.exception.CampaingNotFoundException;
import com.nts.campaignservice.gateway.ampq.CampaignPublisher;
import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@Service
@CacheConfig(cacheNames = {"campaign", "campaigns"})
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignPublisher campaignPublisher;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, CampaignPublisher campaignPublisher) {
        this.campaignRepository = campaignRepository;
        this.campaignPublisher = campaignPublisher;
    }

    @Cacheable("campaigns")
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAllByEndDateAfter(LocalDate.now());
    }

    @Cacheable("campaign")
    public Campaign getCampaign(UUID id) {
        return campaignRepository.findById(id).orElseThrow(CampaingNotFoundException::new);
    }


    @Caching(
            put = {
                    @CachePut("campaigns"),
                    @CachePut("campaign")
            }
    )
    public Campaign addCampaign(Campaign campaign) {
        List<Campaign> conflictedCampaigns = getCampaignsWithPeriodConflict(campaign.getStartDate(), campaign.getEndDate());
        campaignUpdateDateRule(campaign, conflictedCampaigns);

        campaign.setId(UUID.randomUUID());
        Campaign newCampaign = campaignRepository.save(campaign);

        campaignPublisher.announcesCampaignChange(newCampaign, CampaignPublisher.RoutingKey.NEW);

        return newCampaign;
    }

    @Async
    public Future<List<Campaign>> campaignUpdateDateRule(Campaign campaign, List<Campaign> conflictedCampaigns) {
        conflictedCampaigns.forEach(c -> {
            c.setEndDate(c.getEndDate().plusDays(1L));
            while (containsDiferentCampaignWithSameEndDate(campaign, conflictedCampaigns, c))
                c.setEndDate(c.getEndDate().plusDays(1L));
        });

        campaignRepository.saveAll(conflictedCampaigns);
        conflictedCampaigns.forEach(c -> campaignPublisher.announcesCampaignChange(c, CampaignPublisher.RoutingKey.UPDATED));

        return new AsyncResult<>(conflictedCampaigns);
    }

    private boolean containsDiferentCampaignWithSameEndDate(Campaign campaign, List<Campaign> conflictedCampaigns, Campaign c) {
        return c.getEndDate().isEqual(campaign.getEndDate()) || conflictedCampaigns.stream().anyMatch(cc -> cc.getEndDate().isEqual(c.getEndDate()) && !cc.getId().equals(c.getId()));
    }

    @CacheEvict(value = "campaign", key = "#id")
    public void deleteCampaign(UUID id) {
        campaignRepository.deleteById(id);
        campaignPublisher.announcesCampaignChange(new Campaign(id), CampaignPublisher.RoutingKey.DELETED);
    }

    @CachePut("campaign")
    public Campaign updateCampaign(Campaign campaign) {
        Campaign updatedCampaign = campaignRepository.save(campaign);
        campaignPublisher.announcesCampaignChange(updatedCampaign, CampaignPublisher.RoutingKey.UPDATED);
        return updatedCampaign;
    }

    private List<Campaign> getCampaignsWithPeriodConflict(LocalDate startDate, LocalDate endDate) {
        return campaignRepository.findAllByStartDateBetweenOrEndDateBetween(startDate, endDate, startDate, endDate);
    }
}
