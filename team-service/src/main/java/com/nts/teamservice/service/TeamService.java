package com.nts.teamservice.service;

import com.nts.teamservice.exception.TeamNotFoundException;
import com.nts.teamservice.gateway.database.entity.Team;
import com.nts.teamservice.gateway.database.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeam(UUID id) {
        return teamRepository.findById(id).orElseThrow(TeamNotFoundException::new);
    }

    public Team addTeam(Team team) {
        team.setId(UUID.randomUUID());
        return teamRepository.save(team);
    }

    public void deleteTeam(UUID id) {
        teamRepository.deleteById(id);
    }

    public Team updateTeam(Team team) {
        return teamRepository.save(team);
    }
}
