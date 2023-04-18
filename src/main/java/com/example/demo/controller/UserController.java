package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/user-list")
    public List<User> getUserList(){
        return userService.getUsers();
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody User user){
        User users = userRepository.findByLoginAndPassword(user.getLogin(),user.getPassword());
        if (users != null){
            return ResponseEntity.ok(HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
