// Archivo: src/main/java/com/upeu/reportingservice/repository/ReportRepository.java
package com.upeu.reportingservice.repository;

import com.upeu.reportingservice.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByReportCode(String reportCode);
}
