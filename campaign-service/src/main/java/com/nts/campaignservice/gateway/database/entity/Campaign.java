package com.nts.campaignservice.gateway.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Campaign implements Serializable {

    @Id
    private UUID id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID favouriteTeam;

    public Campaign(UUID id) {
        this.id = id;
    }

}
