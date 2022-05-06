package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.Person;
import com.vaadin.tutorial.crm.backend.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    //Methods

    //return all the people that pass the filter by name or last name
    public List<Person> findAllPeople(String filterText){
        if (filterText == null || filterText.isEmpty()){
            return personRepository.findAll();
        }else{
            String[] split = filterText.split(" ");
            String first = filterText;
            String last = filterText;
            if (split.length > 1) {
                first = split[0];
                last = split[1];
            }
            return personRepository.findByNameContainingOrLastNameContainingOrIdNumberContainingAllIgnoreCase(first, last, first);
        }
    }

    //return all the students
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    //count the total of students
    public long count() {
        return personRepository.count();
    }

    //Delete a student
    public void delete(Person contact) {
        personRepository.delete(contact);
    }

    //Save a student
    public void save(Person contact) {
        if (contact == null) {
            return;
        }
        personRepository.save(contact);
    }

    public List<Person> findByName(String name){
        return personRepository.findByNameContainingAllIgnoreCase(name);
    }

    public List<Person> findByLastName(String lastName){
        return personRepository.findByLastNameContainingAllIgnoreCase(lastName);
    }

    public List<Person> findById(String id){
        return personRepository.findByIdNumberContaining(id);
    }

    public List<Person> findByEmail(String email){
        return personRepository.findByEmailContainingAllIgnoreCase(email);
    }

}
