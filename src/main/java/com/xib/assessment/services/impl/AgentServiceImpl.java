package com.xib.assessment.services.impl;

import com.xib.assessment.models.Agent;
import com.xib.assessment.models.Manager;
import com.xib.assessment.models.Team;
import com.xib.assessment.repositories.AgentRepository;
import com.xib.assessment.repositories.ManagerRepository;
import com.xib.assessment.repositories.TeamRepository;
import com.xib.assessment.resources.dto.AgentDTO;
import com.xib.assessment.services.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
@Service
public class AgentServiceImpl implements AgentService {
    private final static Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

    private final AgentRepository agentRepository;
    private final TeamRepository teamRepository;
    private final ManagerRepository managerRepository;

    public AgentServiceImpl(AgentRepository agentRepository, TeamRepository teamRepository,
                            ManagerRepository managerRepository) {
        this.agentRepository = agentRepository;
        this.teamRepository = teamRepository;
        this.managerRepository = managerRepository;
    }

    @Transactional
    @Override
    public Agent save(Agent agent) throws Exception {
        log.info("Request to add a new Agent : {} ", agent);
        Team team = agent.getTeam();
        if (team != null && team.getManagers() != null) {
            if (!checkIfAgentAndTeamHaveSameManager(agent)) {
                throw new Exception("Sorry, An agent can only be assigned to a team that is managed by the same manager he reports to");
            }
        }
        return agentRepository.save(agent);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Agent> findAllAgent(Pageable pageable) {
        log.info("Request to get all Agents by pageable : {} ");
        return agentRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Agent> findAll() {
        log.info("Request to get all Agents : {} ");
        return agentRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Agent findById(Long id) throws Exception {
        log.info("Request to get an agent by Id: {} ", id);
        return agentRepository.findById(id).orElseThrow(() -> new Exception("No Agent Found For Id : " + id));
    }


    private boolean checkIfAgentAndTeamHaveSameManager(Agent agent) throws Exception {
        Team team = agent.getTeam();
        long counts = 0;
        if (agent.getManager() == null) {
            throw new Exception("Please Update/Set Agent to Have a Manager before you continue");
        }
        if (team != null && team.getId() != null) {
            team = this.teamRepository.findById(team.getId()).orElse(null);
            counts = team.getManagers().stream().filter(manager -> manager.getId() == agent.getManager().getId()).count();
            System.out.println("before save" + team);
            team.setHasAgent(true);
            team = teamRepository.save(team);
            System.out.println("after save " + team);
        }
        return counts != 0;
    }

    @Override
    public List<AgentDTO> searchAgent(String query){
        return this.agentRepository.searchAgent(query);
    }

}
