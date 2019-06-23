package com.nts.customerservice.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CampaignDTO {

    private UUID id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private UUID favouriteTeam;

}
