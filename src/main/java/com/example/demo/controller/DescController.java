package com.example.demo.controller;

import com.example.demo.dto.Desc;
import com.example.demo.model.Student;
import com.example.demo.repository.DescRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DescController {

    private final DescRepository descRepository;


    @GetMapping("/list")
    public List<Desc> getUserList() {
        return descRepository.findAll();
    }

    @PostMapping("/desc/add")
    public ResponseEntity<?> addUser(@RequestBody Desc desc) {
        descRepository.save(desc);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
