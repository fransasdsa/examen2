// Archivo: src/main/java/com/upeu/thesisservice/repository/ThesisRepository.java
package com.upeu.thesisservice.repository;

import com.upeu.thesisservice.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThesisRepository extends JpaRepository<Thesis, Long> {
    Optional<Thesis> findByThesisCode(String thesisCode);
}
