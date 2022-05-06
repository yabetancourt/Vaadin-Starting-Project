package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameOrLastNameAllIgnoreCase(String firstname, String lastname);
    List<Student> findByNameContainingOrLastNameContainingOrIdNumberContainingAllIgnoreCase(String firstname, String lastname, String id);
    List<Student> findByIdNumberContaining(String id);
}
