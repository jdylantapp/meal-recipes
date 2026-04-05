package com.jdylantapp.meal_recipes_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdylantapp.meal_recipes_backend.dto.AuthRequest;
import com.jdylantapp.meal_recipes_backend.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService auth;

    //REGISTER USER
    @PostMapping("/register")
    public String registerUser(@RequestBody AuthRequest request) {
        String token = auth.register(request.getEmail(), request.getPassword());
        return token;
    }

    //LOGIN USER
    @PostMapping("/login")
    public String loginUser(@RequestBody AuthRequest request) {
        String token = auth.login(request.getEmail(), request.getPassword());
        return token;
    }
    
    
}
