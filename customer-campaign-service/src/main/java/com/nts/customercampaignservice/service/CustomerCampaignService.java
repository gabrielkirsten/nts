package com.nts.customercampaignservice.service;

import com.nts.customercampaignservice.domain.CustomerDTO;
import com.nts.customercampaignservice.gateway.database.entity.Campaign;
import com.nts.customercampaignservice.gateway.database.entity.CampaignCustomer;
import com.nts.customercampaignservice.gateway.database.repository.CampaignCustomerRepository;
import com.nts.customercampaignservice.gateway.database.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerCampaignService {

    private final CampaignCustomerRepository campaignCustomerRepository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public CustomerCampaignService(CampaignCustomerRepository campaignCustomerRepository, CampaignRepository campaignRepository) {
        this.campaignCustomerRepository = campaignCustomerRepository;
        this.campaignRepository = campaignRepository;
    }

    public List<CampaignCustomer> getCampaignByCustomerId(UUID customerId) {
        return campaignCustomerRepository.getByIdCustomerId(customerId);
    }

    public void associateCampaigns(CustomerDTO customerDTO) {
        List<Campaign> campaigns = campaignRepository.findAllByFavouriteTeam(customerDTO.getFavouriteTeam());

        List<CampaignCustomer> campaignCustomers = campaigns.stream()
                .map(c -> new CampaignCustomer(customerDTO.getId(), c.getId()))
                .collect(Collectors.toList());

        campaignCustomerRepository.saveAll(campaignCustomers);
    }

}
