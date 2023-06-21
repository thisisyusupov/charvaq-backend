package com.example.demo.controller.auth;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.auth.User;
import com.example.demo.repository.auth.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.security.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserJwtController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginDto loginVM){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword()));
        User user = userRepository.findByLogin(loginVM.getUsername());
        if (user == null){
            throw new UsernameNotFoundException("Bu foydalanuvch mavjut emas");
        }
        String token = jwtTokenProvider.createToken(user.getLogin(), user.getRoles());
        Map<Object, Object> map = new HashMap<>();
        map.put("username", user.getLogin());
        map.put("token", token);
        return ResponseEntity.ok(map);
    }
}
