package com.example.demo.controller.auth;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.auth.User;
import com.example.demo.repository.auth.UserRepository;
import com.example.demo.security.JwtResponse;
import com.example.demo.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserJwtController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final UserService userService;


    @PostMapping("/auth/login")
    public HttpEntity<?> login(@RequestBody @Valid LoginDto loginDto){
        JwtResponse jwtResponse = userService.login(loginDto);
        return ResponseEntity.status(jwtResponse.isSuccess() ? 200 : 403).body(jwtResponse);
    }
}
