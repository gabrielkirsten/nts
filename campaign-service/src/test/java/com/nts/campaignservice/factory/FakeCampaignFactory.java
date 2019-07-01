package com.nts.campaignservice.factory;

import com.nts.campaignservice.gateway.database.entity.Campaign;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakeCampaignFactory {

    public static Campaign createAFakeCampaign() {
        Campaign campaign = new Campaign();

        campaign.setId(UUID.randomUUID());
        campaign.setName("Campaing x");
        campaign.setStartDate(LocalDate.now());
        campaign.setEndDate(LocalDate.now().plusDays(5L));
        campaign.setFavouriteTeam(UUID.randomUUID());

        return campaign;
    }

    public static List<Campaign> createAFakeCampaignList() {
        List<Campaign> campaignList = new ArrayList<>();

        for(long i = 0; i < 5; i++){
            Campaign campaign = createAFakeCampaign();
            campaign.setEndDate(LocalDate.now().plusDays(1L + i));
            campaignList.add(campaign);
        }

        return campaignList;
    }

}
