// Archivo: src/main/java/com/upeu/reportingservice/service/ReportService.java
package com.upeu.reportingservice.service;

import com.upeu.reportingservice.entity.Report;
import com.upeu.reportingservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Optional<Report> getReportByCode(String reportCode) {
        return reportRepository.findByReportCode(reportCode);
    }

    public Report createReport(Report report) {
        report.setTimestamp(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report reportDetails) {
        return reportRepository.findById(id).map(report -> {
            report.setReportCode(reportDetails.getReportCode());
            report.setTitle(reportDetails.getTitle());
            report.setDescription(reportDetails.getDescription());
            report.setGeneratedBy(reportDetails.getGeneratedBy());
            // No actualizar timestamp en actualización
            return reportRepository.save(report);
        }).orElseGet(() -> {
            reportDetails.setId(id);
            reportDetails.setTimestamp(LocalDateTime.now());
            return reportRepository.save(reportDetails);
        });
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

    public byte[] generateReport(String reportCode) throws JRException {
        Optional<Report> reportOpt = reportRepository.findByReportCode(reportCode);
        if (!reportOpt.isPresent()) {
            throw new JRException("Reporte no encontrado con código: " + reportCode);
        }
        Report report = reportOpt.get();

        // Cargar el archivo JRXML desde la carpeta resources/reports
        Resource resource = new ClassPathResource("reports/report_template.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

        // Crear fuente de datos
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(report));

        // Parámetros del reporte
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        // Exportar a PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
