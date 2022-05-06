package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class University {

    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String acronym;

    private String name;

    //Constructors
    public University(String name, String acronym){
        this.name = name;
        this.acronym = acronym;
    }

    public University(){
        name = "";
        acronym = "";
    }

    //Methods
    public boolean isPersisted() {
        return id != null;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        University other = (University) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }

    //getters and setters
    public Long getId() {
        return id;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
