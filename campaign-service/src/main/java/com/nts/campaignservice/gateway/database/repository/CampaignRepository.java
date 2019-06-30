package com.nts.campaignservice.gateway.database.repository;

import com.nts.campaignservice.gateway.database.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, UUID> {
    Page<Campaign> findAllByEndDateAfter(LocalDate startDateAfter, Pageable pageable);
    List<Campaign> findAllByStartDateBetweenOrEndDateBetween(LocalDate startDateOfStartDate, LocalDate endDateOfStartDate, LocalDate startDateOfEndDate, LocalDate endDateOfEndDate);
}
