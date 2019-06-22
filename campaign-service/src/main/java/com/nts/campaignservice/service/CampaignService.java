package com.nts.campaignservice.service;

import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.exception.CampaingNotFoundException;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import com.nts.campaignservice.gateway.ampq.CampaignMessageBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final CampaignMessageBroker campaignMessageBroker;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, CampaignMessageBroker campaignMessageBroker) {
        this.campaignRepository = campaignRepository;
        this.campaignMessageBroker = campaignMessageBroker;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAllByEndDateAfter(LocalDateTime.now());
    }

    public Campaign getCampaign(UUID id) {
        return campaignRepository.findById(id).orElseThrow(CampaingNotFoundException::new);
    }

    public Campaign addCampaign(Campaign campaign) {
        Campaign newCampaign = campaignRepository.save(campaign);
        campaignMessageBroker.announcesCampaignChange(newCampaign, CampaignMessageBroker.RoutingKey.NEW);
        return newCampaign;
    }

    public void deleteCampaign(UUID id) {
        campaignRepository.deleteById(id);
        campaignMessageBroker.announcesCampaignChange(new Campaign(id), CampaignMessageBroker.RoutingKey.DELETED);
    }

    public Campaign updateCampaign(Campaign campaign) {
        Campaign updatedCampaign = campaignRepository.save(campaign);
        campaignMessageBroker.announcesCampaignChange(updatedCampaign, CampaignMessageBroker.RoutingKey.UPDATED);
        return updatedCampaign;
    }
}
