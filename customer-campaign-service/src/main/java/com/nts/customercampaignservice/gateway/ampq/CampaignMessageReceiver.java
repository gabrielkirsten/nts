package com.nts.customercampaignservice.gateway.ampq;

import com.nts.customercampaignservice.config.ampq.AmpqConfigurationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampaignMessageBroker {

    private Logger logger = LoggerFactory.getLogger(CampaignMessageBroker.class);

    private final RabbitTemplate rabbitTemplate;
    private final AmpqConfigurationProperties ampqProperties;

    public enum RoutingKey {
        NEW,
        UPDATED,
        DELETED
    }

    @Autowired
    public CampaignMessageBroker(RabbitTemplate rabbitTemplate, AmpqConfigurationProperties ampqProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.ampqProperties = ampqProperties;
    }

    public void announcesCampaignChange(Campaign campaign, RoutingKey routingKey){
        rabbitTemplate.convertAndSend(ampqProperties.getExchange(), routingKey.name(), campaign);
        logger.debug(String.format("Nem message: %s sent to queue: %s", campaign.toString(), routingKey.name()));
    }

}
