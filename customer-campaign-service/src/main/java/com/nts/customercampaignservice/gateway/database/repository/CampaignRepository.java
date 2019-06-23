package com.nts.customercampaignservice.gateway.database.repository;

import com.nts.customercampaignservice.gateway.database.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

    List<Campaign> findAllByFavouriteTeam(UUID teamId);

}
