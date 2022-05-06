package com.vaadin.tutorial.crm.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NotNull
    @NotEmpty
    protected String name;

    @NotNull
    @NotEmpty
    protected String lastName;

    @NotNull
    @NotEmpty
    protected String email;

    @NotNull
    @NotEmpty
    protected String idNumber;

    protected String address;

    protected String phone;

    protected LocalDate birth_date;

    protected String sex;

    protected String college_degree;

    protected Integer graduation_year;

    protected Integer years_experience;

    @ManyToOne
    protected University collegeGraduate;

    //getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAddress() {return address;}

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {return phone;}

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirth_date() {return birth_date;}

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getSex() {return sex;}

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege_degree() {return college_degree;}

    public void setCollege_degree(String college_degree) {
        this.college_degree = college_degree;
    }

    public Integer getGraduation_year() {return graduation_year;}

    public void setGraduation_year(Integer graduation_year) {
        this.graduation_year = graduation_year;
    }

    public Integer getYears_experience() {return years_experience;}

    public void setYears_experience(Integer years_experience) {
        this.years_experience = years_experience;
    }

    public University getCollegeGraduate() {return collegeGraduate;}

    public void setCollegeGraduate(University university) {
        this.collegeGraduate = university;
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
        Person other = (Person) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }

}
