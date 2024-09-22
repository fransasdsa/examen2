// Archivo: src/main/java/com/upeu/advisorservice/controller/AdvisorController.java
package com.upeu.advisorservice.controller;

import com.upeu.advisorservice.entity.Advisor;
import com.upeu.advisorservice.service.AdvisorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/advisors")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @GetMapping
    public List<Advisor> getAllAdvisors() {
        return advisorService.getAllAdvisors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Advisor> getAdvisorById(@PathVariable Long id) {
        Optional<Advisor> advisor = advisorService.getAdvisorById(id);
        return advisor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{advisorCode}")
    public ResponseEntity<Advisor> getAdvisorByCode(@PathVariable String advisorCode) {
        Optional<Advisor> advisor = advisorService.getAdvisorByAdvisorCode(advisorCode);
        return advisor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Advisor> createAdvisor(@Valid @RequestBody Advisor advisor) {
        Advisor createdAdvisor = advisorService.createAdvisor(advisor);
        return ResponseEntity.ok(createdAdvisor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Advisor> updateAdvisor(@PathVariable Long id, @Valid @RequestBody Advisor advisorDetails) {
        Advisor updatedAdvisor = advisorService.updateAdvisor(id, advisorDetails);
        return ResponseEntity.ok(updatedAdvisor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvisor(@PathVariable Long id) {
        advisorService.deleteAdvisor(id);
        return ResponseEntity.noContent().build();
    }
}
