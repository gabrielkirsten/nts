package com.nts.customerservice.gateway.client.fallback;

import com.nts.customerservice.gateway.client.CustomerCampaignClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomerCampaignClientFallbackFactory implements FallbackFactory<CustomerCampaignClient> {

    @Override
    public CustomerCampaignClient create(Throwable cause) {
        return new CustomerCampaignClientFallback(cause);
    }
}
