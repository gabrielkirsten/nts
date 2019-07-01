package com.nts.campaignservice.service.campaignRule;

import com.nts.campaignservice.gateway.database.entity.Campaign;

import java.util.List;
import java.util.concurrent.Future;

public interface CampaignRule {

    Future<List<Campaign>> applyRule(Campaign campaign, List<Campaign> conflictedCampaigns);

}
