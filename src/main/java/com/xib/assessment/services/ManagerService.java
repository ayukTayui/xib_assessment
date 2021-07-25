package com.xib.assessment.services;

import com.xib.assessment.models.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ManagerService {

    Manager save(Manager manager);

    Page<Manager> findAllAgent(Pageable pageable);

    List<Manager> findAll();

    Manager findById(Long id);
}
