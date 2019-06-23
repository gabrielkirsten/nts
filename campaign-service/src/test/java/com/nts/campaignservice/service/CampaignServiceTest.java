package com.nts.campaignservice.service;

import com.nts.campaignservice.gateway.ampq.CampaignPublisher;
import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.gateway.database.repository.CampaignRepository;
import org.apache.commons.lang.SerializationUtils;
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
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest {

    @InjectMocks
    private CampaignService campaignService;

    @Mock
    private CampaignRepository campaignRepository;

    @Mock
    private CampaignPublisher campaignPublisher;

    @Before
    public void setUp() throws Exception {
        when(campaignRepository.saveAll(anyList())).thenReturn(new ArrayList<>());
        doNothing().when(campaignPublisher).announcesCampaignChange(any(Campaign.class), any(CampaignPublisher.RoutingKey.class));
    }

    @Test
    public void shouldAddADayInCampaignsOfPeriod() throws ExecutionException, InterruptedException {
        // test goes here!
    }

}