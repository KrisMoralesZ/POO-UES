package org.example.service;

import org.example.persistence.entity.StudentEntity;
import org.example.persistence.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<StudentEntity> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public StudentEntity save(StudentEntity studentEntity) {
        return this.studentRepository.save(studentEntity);
    }

    public void delete(int studentId) {
        this.studentRepository.deleteById(studentId);
    }
}
