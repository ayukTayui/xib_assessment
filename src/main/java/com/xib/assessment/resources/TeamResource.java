package com.xib.assessment.resources;

import com.xib.assessment.models.Agent;
import com.xib.assessment.models.Team;
import com.xib.assessment.services.AgentService;
import com.xib.assessment.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamResource {
    private final static Logger log = LoggerFactory.getLogger(AgentController.class);
    private final TeamService teamService;
    private final AgentService agentService;

    public TeamResource(TeamService teamService, AgentService agentService) {
        this.teamService = teamService;
        this.agentService = agentService;
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> saveTeam(@RequestBody Team team) throws Exception {
        Team result = teamService.save(team);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/teams")
    public ResponseEntity<Team> updateAgent(@RequestBody Team team) throws Exception {
        log.info("Rest request to update agent : {}", team);
        if (team.getId() == null) {
            throw new Exception("Can Not Update Agent Without Id. Please provide Id");
        }
        Team result = teamService.save(team);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> findAgentById(@PathVariable("id") Long id) throws Exception {
        log.info("Rest request to find agent by Id : {}", id);
        Team result = teamService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/teams")
    public ResponseEntity<List<Team>> findAllAgents() {
        log.info("Rest request to get all agents : {}");
        List<Team> results = teamService.findAll();
        for (Team t : results
        ) {
            System.out.println(t);

        }
        return ResponseEntity.ok(results);
    }

    @PutMapping("/teams/{id}/agent")
    public ResponseEntity<Agent> assignAgent(@RequestBody Team team, @PathVariable("id") Long agentId) throws Exception {
        log.info("Rest request to assign an agent to a team : {}", team);
        if (agentId == null || agentId == 0) {
            throw new Exception("Please provide an agent Id to be able to update Agent");
        }
        if (team.getId() == null || team.getId() == 0) {
            throw new Exception("Please provide team Id to be able to update team");
        }
        Agent retrievedAgent = this.agentService.findById(agentId);

        retrievedAgent.setTeam(team);

        Agent result = this.agentService.save(retrievedAgent);
        return new ResponseEntity(result, HttpStatus.ACCEPTED);
    }

    @GetMapping("/teams/no")
    public ResponseEntity<List<Team>> teamsWithNoManager() throws Exception {
        log.info("Rest request to find teams with no manager : {}");
        List<Team> result = teamService.teamsWithNoManagers();
        return ResponseEntity.ok(result);
    }


}
