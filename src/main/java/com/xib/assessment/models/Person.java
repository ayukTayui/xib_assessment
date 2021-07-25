package com.xib.assessment.models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class Person implements Serializable {

    private final long serialVersionUID = 1l;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = " last_name", nullable = false)
    private String lastName;

    @Column(name = " id_number", nullable = false)
    private String idNumber;

    @Column(name = "job_position" , nullable = false)
    private String jobPosition;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    @Override
    public String toString() {
        return "Person{" +
                "serialVersionUID=" + serialVersionUID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", jobPosition='" + jobPosition + '\'' +
                '}';
    }
}
