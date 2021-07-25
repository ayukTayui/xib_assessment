package com.xib.assessment.services;

import com.xib.assessment.models.Agent;
import com.xib.assessment.resources.dto.AgentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgentService {

    Agent save(Agent agent) throws Exception;

    Page<Agent> findAllAgent(Pageable pageable);

    List<Agent> findAll();

    Agent findById(Long id) throws Exception;

    List<AgentDTO> searchAgent(String query);
}
