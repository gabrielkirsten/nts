package com.nts.customercampaignservice.service;

import com.nts.customercampaignservice.domain.CustomerDTO;
import com.nts.customercampaignservice.exception.CustomerNotFoundException;
import com.nts.customercampaignservice.gateway.database.entity.Customer;
import com.nts.customercampaignservice.gateway.database.repository.CampaignRepository;
import com.nts.customercampaignservice.gateway.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CampaignRepository campaignRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CampaignRepository campaignRepository) {
        this.customerRepository = customerRepository;
        this.campaignRepository = campaignRepository;
    }

    public Customer associateCampaigns(CustomerDTO customerDTO) {
        Customer customer = Customer.fromDTO(customerDTO);
        customer.setCampaigns(campaignRepository.findAllByFavouriteTeam(customerDTO.getFavouriteTeam()));

        return customerRepository.save(customer);
    }

    public Customer getCampaignByCustomerId(UUID customerId) {
        return customerRepository.findById(customerId).orElseThrow(CustomerNotFoundException::new);
    }
}
