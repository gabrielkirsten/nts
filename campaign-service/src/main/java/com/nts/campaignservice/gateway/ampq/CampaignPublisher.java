package com.nts.campaignservice.gateway.ampq;

import com.nts.campaignservice.config.ampq.AmpqConfigurationProperties;
import com.nts.campaignservice.gateway.database.entity.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampaignPublisher {

    private Logger logger = LoggerFactory.getLogger(CampaignPublisher.class);

    private final RabbitTemplate rabbitTemplate;
    private final AmpqConfigurationProperties ampqProperties;

    public enum RoutingKey {
        NEW,
        UPDATED,
        DELETED
    }

    @Autowired
    public CampaignPublisher(RabbitTemplate rabbitTemplate, AmpqConfigurationProperties ampqProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.ampqProperties = ampqProperties;
    }

    public void announcesCampaignChange(Campaign campaign, RoutingKey routingKey){
        rabbitTemplate.convertAndSend(ampqProperties.getExchange(), routingKey.name(), campaign);
        logger.debug(String.format("Nem message: %s sent to queue: %s", campaign.toString(), routingKey.name()));
    }

}
