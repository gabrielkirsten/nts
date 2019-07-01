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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    public void applyRuleShouldDoNotHaveSameEndDate() throws ExecutionException, InterruptedException {
        List<Campaign> campaigns = createAFakeCampaignList();
        Campaign campaign = createAFakeCampaign();

        Future<List<Campaign>> future = campaignRule.applyRule(campaign, campaigns);

        while(!future.isDone() && !future.isCancelled());

        List<Campaign> campaignsReturned = future.get();
        assertTrue(campaignsReturned.stream()
                .noneMatch(c -> campaignsReturned.stream()
                        .anyMatch(d -> d.getEndDate().equals(c.getEndDate()) && !d.getId().equals(c.getId())))
        );
    }

    @Test
    public void applyRuleShouldNotHaveACampaignWithTheSameEndDate() throws ExecutionException, InterruptedException {
        List<Campaign> campaigns = createAFakeCampaignList();
        Campaign campaign = createAFakeCampaign();

        Future<List<Campaign>> future = campaignRule.applyRule(campaign, campaigns);

        while(!future.isDone() && !future.isCancelled());

        assertTrue(future.get().stream().noneMatch(c -> c.getEndDate().isEqual(campaign.getEndDate())));
    }

    private Campaign createAFakeCampaign() {
        Campaign campaign = new Campaign();

        campaign.setId(UUID.randomUUID());
        campaign.setName("Campaing x");
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5L));
        campaign.setFavouriteTeam(UUID.randomUUID());

        return campaign;
    }

    private List<Campaign> createAFakeCampaignList() {
        List<Campaign> campaignList = new ArrayList<>();

        for(long i = 0; i < 5; i++){
            Campaign campaign = createAFakeCampaign();
            campaign.setEndDate(LocalDate.now().plusDays(1L + i));
            campaignList.add(campaign);
        }

        return campaignList;
    }

}