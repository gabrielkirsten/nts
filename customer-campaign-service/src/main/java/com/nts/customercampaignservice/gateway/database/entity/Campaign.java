package com.nts.customercampaignservice.gateway.database.entity;

import com.nts.customercampaignservice.domain.CampaignDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "CAMPAIGN")
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "FAVOURITE_TEAM")
    private UUID favouriteTeam;

    public static Campaign fromDTO(CampaignDTO campaignDTO) {
        return new Campaign(campaignDTO.getId(), campaignDTO.getName(), campaignDTO.getStartDate(), campaignDTO.getEndDate(), campaignDTO.getFavouriteTeam());
    }
}
