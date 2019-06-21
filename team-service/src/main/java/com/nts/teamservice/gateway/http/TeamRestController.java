package com.nts.teamservice.gateway.http;

import com.nts.teamservice.gateway.database.entity.Team;
import com.nts.teamservice.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Api(value = "Team", description = "REST Api for Team", tags = {"Team"})
@RestController
@RequestMapping("teams")
public class TeamRestController {

    private final TeamService teamService;

    @Autowired
    public TeamRestController(TeamService teamService) {
        this.teamService = teamService;
    }

    @ApiOperation("Get all teams")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Team> getTeam() {
        return teamService.getAllTeams();
    }

    @ApiOperation("Get a team")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team getTeam(@PathVariable("id") UUID id) {
        return teamService.getTeam(id);
    }

    @ApiOperation("Add new team")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team addTeam(@RequestBody Team team) {
        return teamService.addTeam(team);
    }

    @ApiOperation("Delete a team by id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTeam(@PathVariable("id") UUID id) {
        teamService.deleteTeam(id);
    }

    @ApiOperation("Update a team")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Team updateTeam(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

}
