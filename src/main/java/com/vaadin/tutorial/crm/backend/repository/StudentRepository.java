package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
//    @Query("select c from Student c" +
//        "where c.firstName like concat('%', :searchTerm,'%')" +
//        "or c.lastName like concat('%', :searchTerm, '%')"
//    )
//    List<Student> search(@Param("searchTerm") String searchTerm);

    List<Student> findByFirstNameOrLastNameAllIgnoreCase(String firstname, String lastname);
    List<Student> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String firstname, String lastname);

}
