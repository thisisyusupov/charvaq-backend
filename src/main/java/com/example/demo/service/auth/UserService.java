package com.example.demo.service.auth;

import com.example.demo.dto.LoginDto;
import com.example.demo.entity.auth.User;
import com.example.demo.repository.auth.UserRepository;
import com.example.demo.security.JwtProvider;
import com.example.demo.security.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }


    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Boolean checkUserName(String userName){
        return userRepository.existsByLogin(userName);
    }

    public User getByUserName(String userName){
        return userRepository.findByLogin(userName).get();
    }

    public List<User>getUserList(){
        return userRepository.findAll();
    }

    public JwtResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getLogin(),
                    loginDto.getPassword()
            )); //System checks user is exists or not
            User user = (User) authentication.getPrincipal(); //Casts Principal to User
            String token = jwtProvider.generateToken(user.getLogin());
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authentication);


            //This will produce token which begins with "Bearer"

            return new JwtResponse(
                    user.getFirstName(),
                    true,
                    token,
                    user.getSystemRoleName().name()
            );
        } catch (Exception exception) {
            return new JwtResponse(
                    null,
                    false,
                    null,
                    null
            );
        }
    }



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));

    }
}
