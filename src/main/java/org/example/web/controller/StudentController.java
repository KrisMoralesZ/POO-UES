package org.example.web.controller;

import org.example.persistence.entity.StudentEntity;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        return ResponseEntity.ok(this.studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<StudentEntity> addStudent(@RequestBody StudentEntity studentEntity) {
        return ResponseEntity.ok(this.studentService.save(studentEntity));
    }

    @PutMapping
    public ResponseEntity<StudentEntity> updateStudent(@RequestBody StudentEntity studentEntity) {
        return ResponseEntity.ok(this.studentService.save(studentEntity));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<StudentEntity> deleteStudent(@PathVariable int studentId) {
        this.studentService.delete(studentId);
        return ResponseEntity.ok().build();
    }
}
