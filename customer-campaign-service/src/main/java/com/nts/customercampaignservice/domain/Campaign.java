package com.nts.customercampaignservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Campaign implements Serializable {

    private UUID id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private UUID favouriteTeam;

}
