package com.nts.customercampaignservice.gateway.database.entity;

import com.nts.customercampaignservice.gateway.database.entity.embedded.CampaignCustomerId;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CAMPAIGN_CUSTOMER")
@AllArgsConstructor
@Getter
public class CampaignCustomer {

    @EmbeddedId
    private CampaignCustomerId id;


}
