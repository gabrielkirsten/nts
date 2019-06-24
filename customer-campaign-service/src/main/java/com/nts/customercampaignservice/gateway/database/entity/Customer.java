package com.nts.customercampaignservice.gateway.database.entity;

import com.nts.customercampaignservice.domain.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    private UUID id;

    private String fullName;

    private String email;

    private LocalDate birthDate;

    private UUID favouriteTeam;

    private List<Campaign> campaigns;

    public static Customer fromDTO(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getFullName(), customerDTO.getEmail(), customerDTO.getBirthDate(), customerDTO.getFavouriteTeam());
    }

    public Customer(UUID id, String fullName, String email, LocalDate birthDate, UUID favouriteTeam) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.favouriteTeam = favouriteTeam;
    }
}
