package com.nts.teamservice.gateway.database.repository;

import com.nts.teamservice.gateway.database.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
}
