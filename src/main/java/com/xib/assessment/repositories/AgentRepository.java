package com.xib.assessment.repositories;

import com.xib.assessment.models.Agent;
import com.xib.assessment.resources.dto.AgentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    @Query(value = "SELECT first_name, last_name,job_position,manager_id,team_id as firstName from agent a WHERE (a.job_position LIKE '%?1%' OR a.first_name LIKE '%?1%' OR a.last_name LIKE '%?1%' OR a.id LIKE '%?1%' OR a.id_number LIKE '%?1%')", nativeQuery = true)
    List<AgentDTO> searchAgent(String query);


}
