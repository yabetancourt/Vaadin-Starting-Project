package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByNameContaining(String name);
}
