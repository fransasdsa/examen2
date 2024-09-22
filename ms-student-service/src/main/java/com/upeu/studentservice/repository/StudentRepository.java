// Archivo: src/main/java/com/upeu/studentservice/repository/StudentRepository.java
package com.upeu.studentservice.repository;

import com.upeu.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentCode(String studentCode);
    Optional<Student> findByEmail(String email);
}
