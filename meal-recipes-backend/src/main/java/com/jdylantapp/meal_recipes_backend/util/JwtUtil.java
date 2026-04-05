package com.jdylantapp.meal_recipes_backend.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
    
    @Value("${jwt.secret}")
    private String base64String;

    private SecretKey secretKey;

    @PostConstruct
    public void createKey() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64String));
    }

    public String generateToken(String email) {
        String jws = Jwts.builder()
                    .issuer("me")
                    .subject(email)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                    .signWith(secretKey)
                    .compact();
        return jws;
    }

    public String extractEmail(String token) {
        try {
            String email = Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject();
            return email;
        }
        catch(JwtException exception) {
            return null;
        }  
    }

    public boolean isTokenValid(String token) {
            String email = extractEmail(token);
            return email != null;
    }

}
