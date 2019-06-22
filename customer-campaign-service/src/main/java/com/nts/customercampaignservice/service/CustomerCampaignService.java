package com.nts.customercampaignservice.service;

import com.nts.customercampaignservice.gateway.database.entity.CampaignCustomer;
import com.nts.customercampaignservice.gateway.database.repository.CampaignCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerCampaignService {

    private final CampaignCustomerRepository campaignCustomerRepository;

    @Autowired
    public CustomerCampaignService(CampaignCustomerRepository campaignCustomerRepository) {
        this.campaignCustomerRepository = campaignCustomerRepository;
    }

    public List<CampaignCustomer> getCampaignByCustomerId(UUID customerId) {
        return campaignCustomerRepository.getByIdCustomerId(customerId);
    }

}
