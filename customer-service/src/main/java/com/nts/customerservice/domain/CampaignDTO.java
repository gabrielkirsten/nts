package com.nts.customerservice.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CampaignDTO {

    private UUID id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID favouriteTeam;

}
