package com.xib.assessment.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "agent")
public class Agent extends Person implements Serializable {
    private final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Team team;

    @OneToOne
    private Manager manager;

    public Agent() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                super.toString()+
                ", team=" + team +
                ", manager=" + manager +

                '}';
    }
}