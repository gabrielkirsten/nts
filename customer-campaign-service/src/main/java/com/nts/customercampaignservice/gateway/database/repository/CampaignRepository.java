package com.nts.customercampaignservice.gateway.database.repository;

import com.nts.customercampaignservice.gateway.database.entity.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampaignRepository extends MongoRepository<Campaign, UUID> {

    List<Campaign> findAllByFavouriteTeam(UUID teamId);

}
