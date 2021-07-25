package com.xib.assessment.resources;

import com.xib.assessment.models.Manager;
import com.xib.assessment.models.Team;
import com.xib.assessment.services.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManagerResource {
    private final static Logger log = LoggerFactory.getLogger(AgentController.class);
    private ManagerService managerService;

    public ManagerResource(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/managers")
    public ResponseEntity<Manager> saveManager(@RequestBody Manager manager){
        log.info("Rest request to update Manager : {}", manager);
        Manager result = managerService.save(manager);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/managers")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager manager) throws Exception {
        log.info("Rest request to update Manager : {}", manager);
        if (manager.getId() == null) {
            throw new Exception("Can Not Update Manager Without Id. Please provide Id");
        }
        Manager result = managerService.save(manager);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/managers/{id}")
    public ResponseEntity<Manager> findManagerById(@PathVariable Long id) throws Exception {
        log.info("Rest request to find Manager by Id : {}", id);
        Manager result = managerService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/managers")
    public ResponseEntity<List<Manager>> findAllManager() {
        log.info("Rest request to get all agents : {}");
        List<Manager> result = managerService.findAll();
        return ResponseEntity.ok(result);
    }

}
