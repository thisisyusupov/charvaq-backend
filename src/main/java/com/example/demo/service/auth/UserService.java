package com.example.demo.service.auth;

import com.example.demo.entity.auth.User;
import com.example.demo.repository.auth.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Boolean checkUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public User getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

}
