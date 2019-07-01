package com.nts.campaignservice.service.campaignRule.impl;

import com.nts.campaignservice.gateway.ampq.CampaignPublisher;
import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import com.nts.campaignservice.service.campaignRule.CampaignRule;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

@Service
public class CampaignRuleImpl implements CampaignRule {

    private final CampaignRepository campaignRepository;
    private final CampaignPublisher campaignPublisher;

    private CountDownLatch lock = new CountDownLatch(1);

    public CampaignRuleImpl(CampaignRepository campaignRepository, CampaignPublisher campaignPublisher) {
        this.campaignRepository = campaignRepository;
        this.campaignPublisher = campaignPublisher;
    }

    private boolean containsDiferentCampaignWithSameEndDate(Campaign campaign, List<Campaign> conflictedCampaigns, Campaign c) {
        return c.getEndDate().isEqual(campaign.getEndDate()) || conflictedCampaignsHaveACampaignWithTheSameEndDateThatIsnt(conflictedCampaigns, c);
    }

    private boolean conflictedCampaignsHaveACampaignWithTheSameEndDateThatIsnt(List<Campaign> conflictedCampaigns, Campaign c) {
        return conflictedCampaigns.stream().anyMatch(cc -> cc.getEndDate().isEqual(c.getEndDate()) && !cc.getId().equals(c.getId()));
    }

    @Async
    @Override
    public Future<List<Campaign>> applyRule(Campaign campaign, List<Campaign> conflictedCampaigns) {
        conflictedCampaigns.forEach(c -> {
            c.setEndDate(c.getEndDate().plusDays(1L));
            campaignsShouldNotHaveTheSameEndDateAsTheNewCampaign(campaign, conflictedCampaigns, c);
        });

        campaignRepository.saveAll(conflictedCampaigns);
        conflictedCampaigns.forEach(c -> campaignPublisher.announcesCampaignChange(c, CampaignPublisher.RoutingKey.UPDATED));

        lock.countDown();
        return new AsyncResult<>(conflictedCampaigns);
    }

    private void campaignsShouldNotHaveTheSameEndDateAsTheNewCampaign(Campaign campaign, List<Campaign> conflictedCampaigns, Campaign c) {
        while (containsDiferentCampaignWithSameEndDate(campaign, conflictedCampaigns, c))
            c.setEndDate(c.getEndDate().plusDays(1L));
    }

}
