package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.University;
import com.vaadin.tutorial.crm.backend.repository.UniversityRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class UniversityService {
    private UniversityRepository companyRepository;
    public UniversityService(UniversityRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    public List<University> findAll() {
        return companyRepository.findAll();
    }
}

