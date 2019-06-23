package com.nts.customerservice.gateway.client;

import com.nts.customerservice.gateway.client.fallback.CustomerCampaignClientFallbackFactory;
import com.nts.customerservice.gateway.database.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "customer-campaign-service", fallbackFactory = CustomerCampaignClientFallbackFactory.class)
public interface CustomerCampaignClient {

    @PostMapping ("customers")
    void associateCampaigns(Customer customer);

}
