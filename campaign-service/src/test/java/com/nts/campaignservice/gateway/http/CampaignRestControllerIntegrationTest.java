package com.nts.campaignservice.gateway.http;

import com.nts.campaignservice.gateway.database.entity.Campaign;
import com.nts.campaignservice.service.CampaignService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static com.nts.campaignservice.factory.FakeCampaignFactory.createAFakeCampaign;
import static com.nts.campaignservice.factory.FakeCampaignFactory.createAFakeCampaignList;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CampaignRestControllerIntegrationTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CampaignRestController campaignRestController;

    @Mock
    private CampaignService campaignService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(campaignRestController).build();
    }

    @Test
    public void shouldGetAllCampaigns() throws Exception {
        List<Campaign> campaignList = createAFakeCampaignList();
        when(campaignService.getAllCampaigns(anyInt())).thenReturn(new PageImpl<>(campaignList));

        mockMvc.perform(get("/campaigns/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content[0].id").value(campaignList.get(0).getId().toString()))
                .andExpect(jsonPath("$.content[0].name").value(campaignList.get(0).getName()))
                .andExpect(jsonPath("$.content[0].startDate[0]").value(campaignList.get(0).getStartDate().getYear()))
                .andExpect(jsonPath("$.content[0].startDate[1]").value(campaignList.get(0).getStartDate().getMonthValue()))
                .andExpect(jsonPath("$.content[0].startDate[2]").value(campaignList.get(0).getStartDate().getDayOfMonth()))
                .andExpect(jsonPath("$.content[0].endDate[0]").value(campaignList.get(0).getEndDate().getYear()))
                .andExpect(jsonPath("$.content[0].endDate[1]").value(campaignList.get(0).getEndDate().getMonthValue()))
                .andExpect(jsonPath("$.content[0].endDate[2]").value(campaignList.get(0).getEndDate().getDayOfMonth()))
                .andExpect(jsonPath("$.content[0].favouriteTeam").value(campaignList.get(0).getFavouriteTeam().toString()));
    }

    @Test
    public void shouldGetACampaign() throws Exception {
        Campaign campaign = createAFakeCampaign();
        when(campaignService.getCampaign(any(UUID.class))).thenReturn(campaign);

        mockMvc.perform(get("/campaigns/" + campaign.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(campaign.getId().toString()))
                .andExpect(jsonPath("$.name").value(campaign.getName()))
                .andExpect(jsonPath("$.startDate[0]").value(campaign.getStartDate().getYear()))
                .andExpect(jsonPath("$.startDate[1]").value(campaign.getStartDate().getMonthValue()))
                .andExpect(jsonPath("$.startDate[2]").value(campaign.getStartDate().getDayOfMonth()))
                .andExpect(jsonPath("$.endDate[0]").value(campaign.getEndDate().getYear()))
                .andExpect(jsonPath("$.endDate[1]").value(campaign.getEndDate().getMonthValue()))
                .andExpect(jsonPath("$.endDate[2]").value(campaign.getEndDate().getDayOfMonth()))
                .andExpect(jsonPath("$.favouriteTeam").value(campaign.getFavouriteTeam().toString()));
    }

    @Test
    public void shouldPostANewCampaign() throws Exception {
        Campaign campaign = createAFakeCampaign();
        when(campaignService.addCampaign(any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(post("/campaigns/").accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\" : \"Campaign x\", " +
                        "\"startDate\" : \"" + campaign.getStartDate().toString() + "\"," +
                        "\"endDate\" : \"" + campaign.getEndDate().toString() + "\"," +
                        "\"favouriteTeam\" : \"" + campaign.getFavouriteTeam() + "\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(campaign.getId().toString()))
                .andExpect(jsonPath("$.name").value(campaign.getName()))
                .andExpect(jsonPath("$.startDate[0]").value(campaign.getStartDate().getYear()))
                .andExpect(jsonPath("$.startDate[1]").value(campaign.getStartDate().getMonthValue()))
                .andExpect(jsonPath("$.startDate[2]").value(campaign.getStartDate().getDayOfMonth()))
                .andExpect(jsonPath("$.endDate[0]").value(campaign.getEndDate().getYear()))
                .andExpect(jsonPath("$.endDate[1]").value(campaign.getEndDate().getMonthValue()))
                .andExpect(jsonPath("$.endDate[2]").value(campaign.getEndDate().getDayOfMonth()))
                .andExpect(jsonPath("$.favouriteTeam").value(campaign.getFavouriteTeam().toString()));

    }

    @Test
    public void shouldPutACampaign() throws Exception {
        Campaign campaign = createAFakeCampaign();
        when(campaignService.updateCampaign(any(Campaign.class))).thenReturn(campaign);

        mockMvc.perform(put("/campaigns/").accept(MediaType.APPLICATION_JSON)
                .contentType(APPLICATION_JSON_UTF8)
                .content("{\"name\" : \"Campaign x\", " +
                        "\"startDate\" : \"" + campaign.getStartDate().toString() + "\"," +
                        "\"endDate\" : \"" + campaign.getEndDate().toString() + "\"," +
                        "\"favouriteTeam\" : \"" + campaign.getFavouriteTeam() + "\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(campaign.getId().toString()))
                .andExpect(jsonPath("$.name").value(campaign.getName()))
                .andExpect(jsonPath("$.startDate[0]").value(campaign.getStartDate().getYear()))
                .andExpect(jsonPath("$.startDate[1]").value(campaign.getStartDate().getMonthValue()))
                .andExpect(jsonPath("$.startDate[2]").value(campaign.getStartDate().getDayOfMonth()))
                .andExpect(jsonPath("$.endDate[0]").value(campaign.getEndDate().getYear()))
                .andExpect(jsonPath("$.endDate[1]").value(campaign.getEndDate().getMonthValue()))
                .andExpect(jsonPath("$.endDate[2]").value(campaign.getEndDate().getDayOfMonth()))
                .andExpect(jsonPath("$.favouriteTeam").value(campaign.getFavouriteTeam().toString()));
    }

    @Test
    public void shouldDeleteACampaign() throws Exception {
        Campaign campaign = createAFakeCampaign();
        doNothing().when(campaignService).deleteCampaign(any(UUID.class));

        mockMvc.perform(delete("/campaigns/" + campaign.getId()).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

}