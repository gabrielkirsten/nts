package com.nts.customercampaignservice.gateway.ampq;

import com.nts.customercampaignservice.domain.CampaignDTO;
import com.nts.customercampaignservice.gateway.database.entity.Campaign;
import com.nts.customercampaignservice.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampaignMessageReceiver {

    private Logger logger = LoggerFactory.getLogger(CampaignMessageReceiver.class);

    private final CampaignService campaignService;

    @Autowired
    public CampaignMessageReceiver(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    public enum RoutingKey {
        NEW,
        UPDATED,
        DELETED
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ampq.queueNew}", durable = "false"),
            exchange = @Exchange(value = "${ampq.exchange}", type = "topic"),
            key = "NEW"
    ))
    public void receiveNewMessage(CampaignDTO campaignDTO) {
        logger.debug(String.format("New entity received: %s", campaignDTO.toString()));
        campaignService.addOrUpdateCampaign(Campaign.fromDTO(campaignDTO));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ampq.queueUpdated}", durable = "false"),
            exchange = @Exchange(value = "${ampq.exchange}", type = "topic"),
            key = "UPDATED"
    ))
    public void receiveUpdatedMessage(CampaignDTO campaignDTO) {
        logger.debug(String.format("Received entity updated: %s", campaignDTO.toString()));
        campaignService.addOrUpdateCampaign(Campaign.fromDTO(campaignDTO));
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ampq.queueDeleted}", durable = "false"),
            exchange = @Exchange(value = "${ampq.exchange}", type = "topic"),
            key = "DELETED"
    ))
    public void receiveMessage(CampaignDTO campaignDTO) {
        logger.debug(String.format("Received entity deleted: %s", campaignDTO.toString()));
        campaignService.deleteCampaign(Campaign.fromDTO(campaignDTO));
    }



}
