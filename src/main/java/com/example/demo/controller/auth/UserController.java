package com.example.demo.controller.auth;

import com.example.demo.entity.auth.User;
import com.example.demo.security.SecurityUtils;
import com.example.demo.service.auth.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user){
        if (!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("Parol uznuligi 4 dan kam", HttpStatus.BAD_REQUEST);
        }
        if (userService.checkUserName(user.getUsername())){
            return new ResponseEntity("Bu user Oldin ro'yxatdan o'tgan", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.create(user));
    }

    private Boolean checkPasswordLength(String password){
        return password.length() >= 4;
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount(){
        String login = SecurityUtils.getCurrentUserName().get();
        User user = userService.getByUserName(login);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/admin-list")
    public List<User> getUserList(){
        return userService.getUserList();
    }
}

