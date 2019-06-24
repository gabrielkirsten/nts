package com.nts.customerservice.gateway.database.repository;

import com.nts.customerservice.gateway.database.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, UUID> {
    boolean existsByEmail(String email);
}
