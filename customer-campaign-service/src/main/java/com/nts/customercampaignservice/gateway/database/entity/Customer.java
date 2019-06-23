package com.nts.customercampaignservice.gateway.database.entity;

import com.nts.customercampaignservice.domain.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BIRTH_DATE")
    private LocalDate birthDate;

    @Column(name = "FAVOURITE_TEAM")
    private UUID favouriteTeam;

    @ManyToMany
    @JoinTable(
            name = "CUSTOMERS_CAMPAIGNS",
            joinColumns = {@JoinColumn(name = "CUSTOMER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "CAMPAIGN_ID")}
    )
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
