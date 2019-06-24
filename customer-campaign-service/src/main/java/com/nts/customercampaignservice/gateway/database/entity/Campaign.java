package com.nts.customercampaignservice.gateway.database.entity;

import com.nts.customercampaignservice.domain.CampaignDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campaign {

    @Id
    private UUID id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID favouriteTeam;

    public static Campaign fromDTO(CampaignDTO campaignDTO) {
        return new Campaign(campaignDTO.getId(), campaignDTO.getName(), campaignDTO.getStartDate(), campaignDTO.getEndDate(), campaignDTO.getFavouriteTeam());
    }
}
