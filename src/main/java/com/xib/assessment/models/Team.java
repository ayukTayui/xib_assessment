package com.xib.assessment.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Team implements Serializable {
    private final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "false")
    private Boolean hasAgent;
    private Boolean hasManager = false;


    @OneToMany(fetch = FetchType.EAGER)
    private List<Manager> managers;

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHasAgent() {
        return hasAgent;
    }

    public void setHasAgent(Boolean hasAgent) {
        this.hasAgent = hasAgent;
    }

    public Boolean getHasManager() {
        return hasManager;
    }

    public void setHasManager(Boolean hasManager) {
        this.hasManager = hasManager;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hasAgent=" + hasAgent +
                ", hasManager=" + hasManager +
                ", managers=" + managers +
                '}';
    }
}
