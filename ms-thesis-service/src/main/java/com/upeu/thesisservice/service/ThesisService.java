// Archivo: src/main/java/com/upeu/thesisservice/service/ThesisService.java
package com.upeu.thesisservice.service;

import com.upeu.thesisservice.entity.Thesis;
import com.upeu.thesisservice.repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ThesisService {

    @Autowired
    private ThesisRepository thesisRepository;

    public List<Thesis> getAllTheses() {
        return thesisRepository.findAll();
    }

    public Optional<Thesis> getThesisById(Long id) {
        return thesisRepository.findById(id);
    }

    public Optional<Thesis> getThesisByCode(String thesisCode) {
        return thesisRepository.findByThesisCode(thesisCode);
    }

    public Thesis createThesis(Thesis thesis) {
        thesis.setSubmissionDate(LocalDateTime.now());
        return thesisRepository.save(thesis);
    }

    public Thesis updateThesis(Long id, Thesis thesisDetails) {
        return thesisRepository.findById(id).map(thesis -> {
            thesis.setThesisCode(thesisDetails.getThesisCode());
            thesis.setTitle(thesisDetails.getTitle());
            thesis.setDescription(thesisDetails.getDescription());
            thesis.setStudentCode(thesisDetails.getStudentCode());
            thesis.setAdvisorCode(thesisDetails.getAdvisorCode());
            thesis.setStatus(thesisDetails.getStatus());
            // No actualizar submissionDate en actualización
            return thesisRepository.save(thesis);
        }).orElseGet(() -> {
            thesisDetails.setId(id);
            thesisDetails.setSubmissionDate(LocalDateTime.now());
            return thesisRepository.save(thesisDetails);
        });
    }

    public void deleteThesis(Long id) {
        thesisRepository.deleteById(id);
    }
}
