package com.nts.campaignservice.service;

import com.nts.campaignservice.exception.CampaingNotFoundException;
import com.nts.campaignservice.gateway.ampq.CampaignPublisher;
import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import com.nts.campaignservice.service.campaignRule.CampaignRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@CacheConfig(cacheNames = {"campaign"})
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignPublisher campaignPublisher;
    private final CampaignRule campaignRule;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository,
                           CampaignPublisher campaignPublisher,
                           CampaignRule campaignRule) {
        this.campaignRepository = campaignRepository;
        this.campaignPublisher = campaignPublisher;
        this.campaignRule = campaignRule;
    }

    public Page<Campaign> getAllCampaigns(Integer page) {
        return campaignRepository.findAllByEndDateAfter(LocalDate.now(), PageRequest.of(page, 50));
    }

    @Cacheable(value = "campaign", key = "#id")
    public Campaign getCampaign(UUID id) {
        return campaignRepository.findById(id).orElseThrow(CampaingNotFoundException::new);
    }

    @CachePut(value = "campaign", key = "#campaign.id")
    public synchronized Campaign addCampaign(Campaign campaign) {
        List<Campaign> conflictedCampaigns = getCampaignsWithPeriodConflict(campaign.getStartDate(), campaign.getEndDate());
        campaignRule.applyRule(campaign, conflictedCampaigns);

        campaign.setId(UUID.randomUUID());
        Campaign newCampaign = campaignRepository.save(campaign);

        campaignPublisher.announcesCampaignChange(newCampaign, CampaignPublisher.RoutingKey.NEW);

        return newCampaign;
    }

    @CacheEvict(value = "campaign", key = "#id", beforeInvocation = true)
    public void deleteCampaign(UUID id) {
        campaignRepository.deleteById(id);
        campaignPublisher.announcesCampaignChange(new Campaign(id), CampaignPublisher.RoutingKey.DELETED);
    }

    @CachePut(value = "campaign", key = "#campaign.id")
    public Campaign updateCampaign(Campaign campaign) {
        Campaign updatedCampaign = campaignRepository.save(campaign);
        campaignPublisher.announcesCampaignChange(updatedCampaign, CampaignPublisher.RoutingKey.UPDATED);
        return updatedCampaign;
    }

    private List<Campaign> getCampaignsWithPeriodConflict(LocalDate startDate, LocalDate endDate) {
        return campaignRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate);
    }

}
