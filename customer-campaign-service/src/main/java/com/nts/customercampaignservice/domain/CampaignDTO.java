package com.nts.customercampaignservice.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CampaignDTO implements Serializable {

    private UUID id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private UUID favouriteTeam;

}
