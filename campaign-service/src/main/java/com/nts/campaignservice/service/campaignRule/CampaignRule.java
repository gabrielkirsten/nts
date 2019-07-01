package com.nts.campaignservice.service.campaignRule;

import com.nts.campaignservice.gateway.database.entity.Campaign;

import java.util.List;
public interface CampaignRule {

    List<Campaign> applyRule(Campaign campaign, List<Campaign> conflictedCampaigns);

}
