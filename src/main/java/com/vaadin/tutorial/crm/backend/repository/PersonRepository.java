package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByNameContainingOrLastNameContainingOrIdNumberContainingAllIgnoreCase(String name, String lastName, String id);

    List<Person> findByNameContainingAllIgnoreCase(String name);

    List<Person> findByLastNameContainingAllIgnoreCase(String lastName);

    List<Person> findByIdNumberContaining(String id);

    List<Person> findByEmailContainingAllIgnoreCase(String email);
}
