package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.University;
import com.vaadin.tutorial.crm.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UniversityService {

    //Attributes
    private final UniversityRepository universityRepository;

    //Constructor
    UniversityService(UniversityRepository universityRepository){
        this.universityRepository = universityRepository;
    }

    //Methods
    //return all the universities
    public List<University> findAll(){
        return universityRepository.findAll();
    }

    //Save a university
    public void save(University university){
        if (university != null)
            universityRepository.save(university);
    }

    //put elements if the DB is empty
    @PostConstruct
    public void populateData(){
        if (universityRepository.count() == 0){
            universityRepository.saveAll(
                    Stream.of(
                            "Universidad Central de Las Villas , UCLV",
                            "Universidad de La Habana , UH",
                            "Universidad de Oriente , UO"
                    ).map(
                           university  -> {
                                String[] split = university.split(",");
                                String name = split[0];
                                String acronym = split[1];
                               return new University(name, acronym);
                            }
                    ).collect(Collectors.toList())
            );
        }
    }

}
