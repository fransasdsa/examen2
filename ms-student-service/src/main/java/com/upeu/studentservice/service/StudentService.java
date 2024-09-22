// Archivo: src/main/java/com/upeu/studentservice/service/StudentService.java
package com.upeu.studentservice.service;

import com.upeu.studentservice.entity.Student;
import com.upeu.studentservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getStudentByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setStudentCode(studentDetails.getStudentCode());
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setEmail(studentDetails.getEmail());
            student.setAge(studentDetails.getAge());
            return studentRepository.save(student);
        }).orElseGet(() -> {
            studentDetails.setId(id);
            return studentRepository.save(studentDetails);
        });
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
