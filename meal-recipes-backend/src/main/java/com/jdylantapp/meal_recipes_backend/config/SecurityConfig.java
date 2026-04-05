package com.jdylantapp.meal_recipes_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestClient;

import com.jdylantapp.meal_recipes_backend.filter.JwtAuthFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter filter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http.csrf((csrf) -> csrf.disable());

        http.authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
        );

        http.sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.cors(cors -> cors.disable());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    
}
