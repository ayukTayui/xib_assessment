package com.xib.assessment.services.impl;

import com.xib.assessment.models.Manager;
import com.xib.assessment.models.Team;
import com.xib.assessment.repositories.ManagerRepository;
import com.xib.assessment.repositories.TeamRepository;
import com.xib.assessment.services.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final static Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);
    private final TeamRepository teamRepository;
    private final ManagerRepository managerRepository;

    public TeamServiceImpl(TeamRepository teamRepository,ManagerRepository managerRepository) {
        this.teamRepository = teamRepository;
        this.managerRepository = managerRepository;
    }

    @Transactional
    @Override
    public Team save(Team team) throws Exception {
        log.info("Request to add a new Team : {} ", team);
        if (this.validManagers(team)) {
            List<Manager> managers = team.getManagers();
            team.setManagers(null);
            Team result = teamRepository.save(team);
            result.setManagers(managers);

            return teamRepository.save(result);
        }

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Team findById(Long id) {
        log.info("Request to finding a Team by Id : {} ", id);
        return teamRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Team> findAll() {
        log.info("Request to get all Teams : {} ");
        return teamRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Team> findAllTeam(Pageable pageable) {
        log.info("Request to get all Teams : {} ");
        return teamRepository.findAll(pageable);
    }

    @Override
    public List<Team> teamsWithNoManagers() {

        return this.teamRepository.teamsWithNoManagers();
    }

    private boolean validManagers(Team team) throws Exception {

        if (team.getManagers() != null && !team.getManagers().isEmpty()) {
            long size = team.getManagers().size();
            if (size > 2) {
                throw new Exception("Sorry, A Team Can Only Be mange by At Most 2 Managers");
            } else {
                team.setHasManager(true);
                teamRepository.save(team);
                return true;
            }

        } else {
            return true;
        }
    }


}
