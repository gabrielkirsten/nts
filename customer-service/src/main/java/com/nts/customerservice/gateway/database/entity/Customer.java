package com.nts.customerservice.gateway.database.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Customer {

    @Id
    private UUID id;

    private String fullName;

    private String email;

    private LocalDate birthDate;

    private UUID favouriteTeam;

}
