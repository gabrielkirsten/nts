package com.nts.campaignservice.service;

import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.exception.CampaingNotFoundException;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAllByEndDateAfter(LocalDateTime.now());
    }

    public Campaign getCampaign(UUID id) {
        return campaignRepository.findById(id).orElseThrow(CampaingNotFoundException::new);
    }

    public Campaign addCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(UUID id) {
        campaignRepository.deleteById(id);
    }

    public Campaign updateCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }
}
