package com.nts.customercampaignservice.gateway.ampq;

import com.nts.customercampaignservice.domain.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.stereotype.Component;

@Component
public class CampaignMessageReceiver {

    private Logger logger = LoggerFactory.getLogger(CampaignMessageReceiver.class);

    public enum RoutingKey {
        NEW,
        UPDATED,
        DELETED
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ampq.queue}", durable = "false"),
            exchange = @Exchange(value = "${ampq.exchange}", type = "topic"),
            key = "NEW"
    ))
    public void receiveNewMessage(Campaign campaign) {
        logger.debug(String.format("New entity received: %s", campaign.toString()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ampq.queue}", durable = "false"),
            exchange = @Exchange(value = "${ampq.exchange}", type = "topic"),
            key = "UPDATED"
    ))
    public void receiveUpdatedMessage(Campaign campaign) {
        logger.debug(String.format("Received entity updated: %s", campaign.toString()));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ampq.queue}", durable = "false"),
            exchange = @Exchange(value = "${ampq.exchange}", type = "topic"),
            key = "DELETED"
    ))
    public void receiveMessage(Campaign campaign) {
        logger.debug(String.format("Received entity deleted: %s", campaign.toString()));
    }



}
