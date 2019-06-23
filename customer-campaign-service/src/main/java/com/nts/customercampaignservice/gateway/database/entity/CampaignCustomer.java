package com.nts.customercampaignservice.gateway.database.entity;

import com.nts.customercampaignservice.gateway.database.entity.embedded.CampaignCustomerId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "CAMPAIGN_CUSTOMER")

@Data
@NoArgsConstructor
public class CampaignCustomer {

    @EmbeddedId
    private CampaignCustomerId id;

    public CampaignCustomer(UUID customerId, UUID campaignId) {
        this.id = new CampaignCustomerId(customerId, campaignId);
    }

}
