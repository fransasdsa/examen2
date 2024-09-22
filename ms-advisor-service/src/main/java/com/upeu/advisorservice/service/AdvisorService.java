// Archivo: src/main/java/com/upeu/advisorservice/service/AdvisorService.java
package com.upeu.advisorservice.service;

import com.upeu.advisorservice.entity.Advisor;
import com.upeu.advisorservice.repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    public List<Advisor> getAllAdvisors() {
        return advisorRepository.findAll();
    }

    public Optional<Advisor> getAdvisorById(Long id) {
        return advisorRepository.findById(id);
    }

    public Optional<Advisor> getAdvisorByAdvisorCode(String advisorCode) {
        return advisorRepository.findByAdvisorCode(advisorCode);
    }

    public Advisor createAdvisor(Advisor advisor) {
        return advisorRepository.save(advisor);
    }

    public Advisor updateAdvisor(Long id, Advisor advisorDetails) {
        return advisorRepository.findById(id).map(advisor -> {
            advisor.setAdvisorCode(advisorDetails.getAdvisorCode());
            advisor.setFirstName(advisorDetails.getFirstName());
            advisor.setLastName(advisorDetails.getLastName());
            advisor.setEmail(advisorDetails.getEmail());
            advisor.setSpecialization(advisorDetails.getSpecialization());
            return advisorRepository.save(advisor);
        }).orElseGet(() -> {
            advisorDetails.setId(id);
            return advisorRepository.save(advisorDetails);
        });
    }

    public void deleteAdvisor(Long id) {
        advisorRepository.deleteById(id);
    }
}
