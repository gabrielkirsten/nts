package com.nts.customercampaignservice.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CampaignDTO implements Serializable {

    private UUID id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID favouriteTeam;

}
