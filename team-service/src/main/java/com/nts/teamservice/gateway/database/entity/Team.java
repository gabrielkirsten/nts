package com.nts.teamservice.gateway.database.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Team {

    @Id
    private UUID id;

    private String name;

}
