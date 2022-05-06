package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student extends Person{

    //Attributes
    private String occupation;

    private String work_phone;


    //getters and setters

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getWork_phone() {
        return work_phone;
    }

    public void setWork_phone(String work_phone) {
        this.work_phone = work_phone;
    }

}
