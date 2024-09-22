// Archivo: src/main/java/com/upeu/advisorservice/repository/AdvisorRepository.java
package com.upeu.advisorservice.repository;

import com.upeu.advisorservice.entity.Advisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdvisorRepository extends JpaRepository<Advisor, Long> {
    Optional<Advisor> findByAdvisorCode(String advisorCode);
    Optional<Advisor> findByEmail(String email);
}
