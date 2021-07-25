package com.xib.assessment.services.impl;

import com.xib.assessment.models.Manager;
import com.xib.assessment.models.Team;
import com.xib.assessment.repositories.ManagerRepository;
import com.xib.assessment.repositories.TeamRepository;
import com.xib.assessment.services.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManagerImpl implements ManagerService {
    private final static Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);
    private ManagerRepository managerRepository;
    private TeamRepository teamRepository;


    public ManagerImpl(ManagerRepository managerRepository, TeamRepository teamRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Manager save(Manager manager) {
        log.info("Request to add a new Manager : {} " ,manager);
        return managerRepository.save(manager);
    }

    @Override
    public Page<Manager> findAllAgent(Pageable pageable) {
        log.info("Request to get all Managers : {} " , pageable);
        return managerRepository.findAll(pageable);
    }

    @Override
    public List<Manager> findAll() {
        log.info("Request to get all managers : {} ");
        return managerRepository.findAll();
    }

    @Override
    public Manager findById(Long id) {
        log.info("Request to get a Manager : {} " ,id);
        return managerRepository.findById(id).get();
    }
}
