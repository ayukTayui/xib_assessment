package com.xib.assessment.services;


import com.xib.assessment.models.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeamService {

    Team save(Team team) throws Exception;

    Team findById(Long id);

    List<Team> findAll();

    Page<Team> findAllTeam(Pageable pageable);

    List<Team> teamsWithNoManagers();
}
