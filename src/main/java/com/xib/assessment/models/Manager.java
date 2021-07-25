package com.xib.assessment.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "manager")
public class Manager extends Person implements Serializable {
    private final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                super.toString()+
                '}';
    }
}
