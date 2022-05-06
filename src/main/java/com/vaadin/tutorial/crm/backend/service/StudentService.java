package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Student;
import com.vaadin.tutorial.crm.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class StudentService {

    //Attributes
    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    private final StudentRepository studentRepository;

    //Constructor
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    //Methods
    //return all the students that pass the filter by name or last name
    public List<Student> findAllStudents(String filterText){
        if (filterText == null || filterText.isEmpty()){
            return studentRepository.findAll();
        }else{
            String[] split = filterText.split(" ");
            String first = filterText;
            String last = filterText;
            if (split.length > 1) {
                first = split[0];
                last = split[1];
            }
            return studentRepository.findByNameContainingOrLastNameContainingOrIdNumberContainingAllIgnoreCase(first, last, first);
        }
    }

    //return all the students that pass the filter by id
    public List<Student> findById(String filterText){
        return studentRepository.findByIdNumberContaining(filterText);
    }

    //return all the students
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    //count the total of students
    public long count() {
        return studentRepository.count();
    }

    //Delete a student
    public void delete(Student contact) {
        studentRepository.delete(contact);
    }

    //Save a student
    public void save(Student contact) {
        if (contact == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        studentRepository.save(contact);
    }

}
