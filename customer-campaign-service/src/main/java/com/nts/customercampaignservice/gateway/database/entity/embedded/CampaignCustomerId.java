package com.nts.customercampaignservice.gateway.database.entity.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CampaignCustomerId implements Serializable {

    @Column(name = "CUSTOMER_ID")
    private UUID customerId;

    @Column(name = "CAMPAIGN_ID")
    private UUID campaignId;

}
