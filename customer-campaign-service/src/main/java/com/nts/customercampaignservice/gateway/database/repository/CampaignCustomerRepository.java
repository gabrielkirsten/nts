package com.nts.customercampaignservice.gateway.database.repository;

import com.nts.customercampaignservice.gateway.database.entity.CampaignCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignCustomerRepository extends JpaRepository<CampaignCustomer, UUID> {

    List<CampaignCustomer> getByIdCustomerId(UUID customerId);

}
