package com.nts.customercampaignservice.gateway.http;

import com.nts.customercampaignservice.domain.CustomerDTO;
import com.nts.customercampaignservice.gateway.database.entity.Customer;
import com.nts.customercampaignservice.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Api(value = "Customer campaign", description = "REST Api for Customer campaign", tags = {"Customer", "Campaign"})
@RestController
@RequestMapping("customers")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation("Get all campaigns of a customer")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}/campaign")
    public Customer getCustomerCampaigns(@PathVariable("id") UUID customerId) {
        return customerService.getCampaignByCustomerId(customerId);
    }

    @ApiOperation("Create the client campaign association")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Customer associateCampaigns(@RequestBody CustomerDTO customerDTO){
        return customerService.associateCampaigns(customerDTO);
    }

}
