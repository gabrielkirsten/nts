package com.nts.customercampaignservice.service;

import com.nts.customercampaignservice.gateway.database.entity.Campaign;
import com.nts.customercampaignservice.gateway.database.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public Campaign addOrUpdateCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Campaign campaign) {
        campaignRepository.delete(campaign);
    }

}
