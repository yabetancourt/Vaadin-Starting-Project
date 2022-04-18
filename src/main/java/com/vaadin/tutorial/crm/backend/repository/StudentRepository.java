package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Student;
import com.vaadin.tutorial.crm.backend.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFirstNameOrLastNameAllIgnoreCase(String firstname, String lastname);
    List<Student> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstname, String lastname);


}
