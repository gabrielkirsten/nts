package com.nts.customerservice.service;

import com.nts.customerservice.gateway.client.CustomerCampaignClient;
import com.nts.customerservice.gateway.database.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerCampainsService {

    private final CustomerCampaignClient customerCampaignClient;

    @Autowired
    public CustomerCampainsService(CustomerCampaignClient customerCampaignClient) {
        this.customerCampaignClient = customerCampaignClient;
    }

    void associateCampaigns(Customer customer) {
        customerCampaignClient.associateCampaigns(customer);
    }

}
