package com.xib.assessment.repositories;

import com.xib.assessment.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    @Query(value = "select * from team t  where ( 1 != COALESCE (t.has_agent,0)) OR ( 1 != COALESCE (t.has_manager,0))", nativeQuery = true)
    List<Team> teamsWithNoManagers();


}
