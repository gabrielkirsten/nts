package com.nts.customercampaignservice.gateway.http;

import com.nts.customercampaignservice.gateway.database.entity.CampaignCustomer;
import com.nts.customercampaignservice.service.CustomerCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("customers")
public class CustomerRestController {

    private final CustomerCampaignService customerCampaignService;

    @Autowired
    public CustomerRestController(CustomerCampaignService customerCampaignService) {
        this.customerCampaignService = customerCampaignService;
    }

    @GetMapping("{id}/campaign")
    public List<CampaignCustomer> getCustomerCampaigns(@PathVariable("id") UUID customerId) {
        return customerCampaignService.getCampaignByCustomerId(customerId);
    }

}
