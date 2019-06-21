package com.nts.campaignservice.gateway.database.repository;

import com.nts.campaignservice.gateway.database.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {
    List<Campaign> findAllByEndDateAfter(LocalDateTime startDateAfter);
}
