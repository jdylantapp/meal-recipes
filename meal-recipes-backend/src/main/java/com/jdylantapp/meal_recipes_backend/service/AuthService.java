package com.jdylantapp.meal_recipes_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jdylantapp.meal_recipes_backend.entity.User;
import com.jdylantapp.meal_recipes_backend.repository.UserRepository;
import com.jdylantapp.meal_recipes_backend.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwt;

    //REGISTER A USER
    public String register(String email, String password) {

        if(userRepository.findUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Error: User already exists");
        }

        String passwordHash = encoder.encode(password);

        User newUser = new User(email, passwordHash);
        userRepository.save(newUser);

        String token = jwt.generateToken(email);
        return token;
    }

    //LOGIN USER
    public String login(String email, String password) {

        User user = userRepository.findUserByEmail(email).orElseThrow();

        if(encoder.matches(password, user.getPasswordHash())) {
            return jwt.generateToken(email);
        }
        else {
            throw new IllegalArgumentException("Error: Invalid password");
        }

    }
    
}
