package com.vaadin.tutorial.crm.backend;

public class Person {
    private String name, firstName,lastName, id, email, grade;
    public Person(){
        firstName = "Yadier";
        lastName = "Betancourt Martinez";
        name = firstName  + " " + lastName;
        id = "01080172624";
        email = "yadierbetanc@gmail.com";
        grade = "Doctor";
    }

    public Person(String first, String lastName, String id, String email,String grade){
        firstName = first;
        this.lastName = lastName;
        this.id = id;
        this.email = email;
        name = firstName +" "+ lastName;
        this.grade = grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        for (int i = 0; i < firstName.length(); i++){
            if(Character.isDigit(firstName.charAt(i))){

            }
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
