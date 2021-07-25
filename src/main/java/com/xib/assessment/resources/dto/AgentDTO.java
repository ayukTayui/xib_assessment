package com.xib.assessment.resources.dto;


import java.io.Serializable;

public interface AgentDTO extends Serializable {

    String firstName();

    String lastName();

    String jobPosition();

    Long managerId();

    Long teamId();

}
