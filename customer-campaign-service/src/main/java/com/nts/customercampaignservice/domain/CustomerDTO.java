package com.nts.customercampaignservice.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CustomerDTO {

    private UUID id;
    private UUID favouriteTeam;
    private String fullName;
    private String email;
    private LocalDate birthDate;

}
