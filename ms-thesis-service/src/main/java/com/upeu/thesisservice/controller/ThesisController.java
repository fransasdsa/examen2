// Archivo: src/main/java/com/upeu/thesisservice/controller/ThesisController.java
package com.upeu.thesisservice.controller;

import com.upeu.thesisservice.entity.Thesis;
import com.upeu.thesisservice.service.ThesisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/theses")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @GetMapping
    public List<Thesis> getAllTheses() {
        return thesisService.getAllTheses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Thesis> getThesisById(@PathVariable Long id) {
        Optional<Thesis> thesis = thesisService.getThesisById(id);
        return thesis.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{thesisCode}")
    public ResponseEntity<Thesis> getThesisByCode(@PathVariable String thesisCode) {
        Optional<Thesis> thesis = thesisService.getThesisByCode(thesisCode);
        return thesis.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Thesis> createThesis(@Valid @RequestBody Thesis thesis) {
        Thesis createdThesis = thesisService.createThesis(thesis);
        return ResponseEntity.ok(createdThesis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Thesis> updateThesis(@PathVariable Long id, @Valid @RequestBody Thesis thesisDetails) {
        Thesis updatedThesis = thesisService.updateThesis(id, thesisDetails);
        return ResponseEntity.ok(updatedThesis);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThesis(@PathVariable Long id) {
        thesisService.deleteThesis(id);
        return ResponseEntity.noContent().build();
    }
}
