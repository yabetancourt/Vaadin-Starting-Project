package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Student;
import com.vaadin.tutorial.crm.backend.entity.University;
import com.vaadin.tutorial.crm.backend.repository.StudentRepository;
import com.vaadin.tutorial.crm.backend.repository.UniversityRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StudentService {

    private static final Logger LOGGER = Logger.getLogger(StudentService.class.getName());
    private final StudentRepository studentRepository;
    private final UniversityRepository universityRepository;

    public StudentService(StudentRepository studentRepository, UniversityRepository companyRepository) {
        this.studentRepository = studentRepository;
        this.universityRepository = companyRepository;
    }
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
            return studentRepository.findByFirstNameContainingOrLastNameContainingAllIgnoreCase(first, last);
        }
    }

    public List<Student> findByUni(String universityName){
        University university = new University(universityName);
        List<Student> list = studentRepository.findAll();
        List<Student> ans = new ArrayList<>();
        for(Student st : list){
            if (st.getUniversity().compareTo(university) == 0){
                ans.add(st);
            }
        }
        return ans;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    public long count() {
        return studentRepository.count();
    }
    public void delete(Student contact) {
        studentRepository.delete(contact);
    }
    public void save(Student contact) {
        if (contact == null) {
            LOGGER.log(Level.SEVERE,
                    "Contact is null. Are you sure you have connected your form to the application?");
            return;
        }
        studentRepository.save(contact);
    }

    public List<University> findAllUniversities() {
        return universityRepository.findAll();
    }

    @PostConstruct
    public void populateTestData() {
        if (universityRepository.count() == 0) {
            universityRepository.saveAll(
                    Stream.of("UH", "UCLV", "UO").map(University::new).collect(Collectors.toList())
            );

        }

        if (studentRepository.count() == 0) {
            Random r = new Random(0);
            List<University> companies = universityRepository.findAll();
            studentRepository.saveAll(
                    Stream.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen", "Lois Potur", "Mario Grabb")
                            .map(name -> {
                                String[] split = name.split(" ");
                                Student contact = new Student();
                                contact.setFirstName(split[0]);
                                contact.setLastName(split[1]);
                                contact.setUniversity(companies.get(r.nextInt(companies.size())));
                                String email = (contact.getFirstName() + "." + contact
                                        .getLastName() + "@" + contact.getUniversity().getName().replaceAll("[\\s-]", "") +
                                        ".com").toLowerCase();
                                contact.setEmail(email);
                                return contact;
                            }).collect(Collectors.toList()));
        }
    }
}
