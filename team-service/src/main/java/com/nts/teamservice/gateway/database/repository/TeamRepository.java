package com.nts.teamservice.gateway.database.repository;

import com.nts.teamservice.gateway.database.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface TeamRepository extends MongoRepository<Team, UUID> {
}
