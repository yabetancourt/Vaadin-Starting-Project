package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class University extends AbstractEntity implements Comparable{

    private String name;
    @OneToMany(mappedBy = "university", fetch = FetchType.EAGER)
    private List<Student> students = new LinkedList<>();
    public University() {
        name = "";
    }
    public University(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public int compareTo(Object o) {
        University other = (University) o;
        return this.getName().compareTo(other.getName());
    }
}
