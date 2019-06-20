package com.nts.campaignservice.gateway.http;

import com.nts.campaignservice.domain.Campaign;
import com.nts.campaignservice.service.CampaignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Api(value = "Campaign", description = "REST Api for Campaign", tags = {"Campaign"})
@RestController
@RequestMapping("campaign")
public class CampaignRestController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignRestController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @ApiOperation("Get all campaigns")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Campaign> getCampaign() {
        return campaignService.getAllCampaigns();
    }

    @ApiOperation("Get a campaign")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Campaign getCampaign(@PathVariable("id") UUID id) {
        return campaignService.getCampaign(id);
    }

    @ApiOperation("Add new campaign")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Campaign addCampaign(@RequestBody Campaign campaign) {
        return campaignService.addCampaign(campaign);
    }

    @ApiOperation("Delete a campaign by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCampaign(@PathVariable("id") UUID id) {
        campaignService.deleteCampaign(id);
    }

    @ApiOperation("Update a campaign")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Campaign updateCampaign(@RequestBody Campaign campaign) {
        return campaignService.updateCampaign(campaign);
    }

}
