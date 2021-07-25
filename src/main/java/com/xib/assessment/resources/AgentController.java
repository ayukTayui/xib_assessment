package com.xib.assessment.resources;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xib.assessment.models.Agent;
import com.xib.assessment.models.Team;
import com.xib.assessment.repositories.AgentRepository;
import com.xib.assessment.resources.dto.AgentDTO;
import com.xib.assessment.services.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AgentController {

    private final static Logger log = LoggerFactory.getLogger(AgentController.class);

    private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/agents")
    public ResponseEntity<Agent> saveAgent(@RequestBody Agent agent) throws Exception {
        log.info("Rest request to save agent : {}", agent);
        if (agent.getId() != null) {
            throw new Exception("Id Already Exist for Agent : " + agent.getId());
        }
        Agent result = agentService.save(agent);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/agents")
    public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) throws Exception {
        log.info("Rest request to update agent : {}", agent);
        if (agent.getId() == null) {
            throw new Exception("Can Not Update Agent Without Id. Please provide Id");
        }
        Agent result = agentService.save(agent);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/agents/{id}")
    public ResponseEntity<Agent> findAgentById(@PathVariable("id") Long id) throws Exception {
        log.info("Rest request to find agent by Id : {}", id);
        Agent result = agentService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/agents")
    public ResponseEntity<List<Agent>> findAllAgents() {
        log.info("Rest request to get all agents : {}");
        List<Agent> result = agentService.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/agents/page")
    public ResponseEntity<Page<Agent>> findAllAgentByPageable(@RequestHeader Pageable pageable) {
        log.info("Rest request to get all agent by pageable : {}", pageable);
        Page<Agent> result = agentService.findAllAgent(pageable);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/_search/agents")
    public ResponseEntity<?> searchAllAgentByPageable(@RequestParam(name = "query", required = false) String query, Pageable pageable) throws IOException {
        log.info("Rest request to search  all agent by pageable and query : {}", pageable);
        List<AgentDTO> agentDTOS = null;
        if (query == null || query.isEmpty()) {
            List<Agent> all = this.agentService.findAll();
            return ResponseEntity.ok(all);
        }
        agentDTOS = this.agentService.searchAgent(query);
        return ResponseEntity.ok(agentDTOS);
    }
}
