package com.nts.customercampaignservice.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDTO {

    private UUID id;
    private UUID favouriteTeam;

}
