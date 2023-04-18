package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student-list")
    public List<Student> getUserList() {
        return studentService.getStudents();
    }

    @PostMapping("/student/add")
    public ResponseEntity<?> addUser(@RequestBody Student student) {
        studentService.save(student);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/student/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id){
        studentRepository.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
