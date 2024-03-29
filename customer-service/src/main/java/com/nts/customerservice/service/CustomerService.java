package com.nts.customerservice.service;

import com.nts.customerservice.exception.CustomerNotFoundException;
import com.nts.customerservice.exception.EmailAlreadyExistsException;
import com.nts.customerservice.gateway.database.entity.Customer;
import com.nts.customerservice.gateway.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerCampainsService customerCampainsService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerCampainsService customerCampainsService) {
        this.customerRepository = customerRepository;
        this.customerCampainsService = customerCampainsService;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomer(UUID id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    public Customer addCustomer(Customer customer) {
        if(customerRepository.existsByEmail(customer.getEmail()))
            throw new EmailAlreadyExistsException();

        customer.setId(UUID.randomUUID());
        Customer newCustomer = customerRepository.save(customer);

        customerCampainsService.associateCampaigns(newCustomer);

        return newCustomer;
    }

    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
