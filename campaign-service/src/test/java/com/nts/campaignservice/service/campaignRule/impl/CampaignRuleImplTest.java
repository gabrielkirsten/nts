package com.nts.campaignservice.service.campaignRule.impl;

import com.nts.campaignservice.gateway.ampq.CampaignPublisher;
import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.nts.campaignservice.factory.FakeCampaignFactory.createAFakeCampaign;
import static com.nts.campaignservice.factory.FakeCampaignFactory.createAFakeCampaignList;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CampaignRuleImplTest {

    @InjectMocks
    private CampaignRuleImpl campaignRule;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignPublisher campaignPublisher;

    @Before
    public void setUp() {
        doNothing().when(campaignPublisher).announcesCampaignChange(any(Campaign.class), any(CampaignPublisher.RoutingKey.class));
        when(campaignRepository.saveAll(anyList())).thenReturn(null);
    }

    @Test
    public void applyRuleShouldDoNotHaveSameEndDate() {
        List<Campaign> campaigns = createAFakeCampaignList();
        Campaign campaign = createAFakeCampaign();

        campaignRule.applyRule(campaign, campaigns);

        assertTrue(campaigns.stream()
                .noneMatch(c -> campaigns.stream()
                        .anyMatch(d -> d.getEndDate().equals(c.getEndDate()) && !d.getId().equals(c.getId())))
        );
    }

    @Test
    public void applyRuleShouldNotHaveACampaignWithTheSameEndDate() {
        List<Campaign> campaigns = createAFakeCampaignList();
        Campaign campaign = createAFakeCampaign();

        campaignRule.applyRule(campaign, campaigns);

        assertTrue(campaigns.stream().noneMatch(c -> c.getEndDate().isEqual(campaign.getEndDate())));
    }


}