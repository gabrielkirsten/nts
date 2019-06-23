package com.nts.customerservice.gateway.client.fallback;

import com.nts.customerservice.gateway.client.CustomerCampaignClient;
import com.nts.customerservice.gateway.database.entity.Customer;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerCampaignClientFallbackFactory implements CustomerCampaignClient {

    private final Throwable cause;
    private Logger logger = LoggerFactory.getLogger(CustomerCampaignClientFallbackFactory.class);

    public CustomerCampaignClientFallbackFactory(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public void associateCampaigns(Customer customer) {
        logger.error("FAIL TO ASSOCIATE CAMPAIGNS");

        if (cause instanceof FeignException) {
            logger.error("[CUSTOMER CAMPAIGN SERVICE] HTTP Error " + ((FeignException) cause).status());
        } else {
            logger.error("[CUSTOMER CAMPAIGN SERVICE] Unknown error!");
        }

    }

}
