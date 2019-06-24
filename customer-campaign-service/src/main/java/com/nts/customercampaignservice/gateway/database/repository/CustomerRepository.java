package com.nts.customercampaignservice.gateway.database.repository;

import com.nts.customercampaignservice.gateway.database.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {
}
