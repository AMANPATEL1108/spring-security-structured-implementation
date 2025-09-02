package com.structuredsprindsecurity.springsecuritystructured.api.serviceImpl;

import com.structuredsprindsecurity.springsecuritystructured.api.model.User;
import com.structuredsprindsecurity.springsecuritystructured.api.service.JwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtServiceImplementation implements JwtService {
    private final String secretKey = "12345678wertyu@#$%^&*SDFGH2#$%^&*@#$%^&DFVBN@#$%RTG$%TG(*&YTGTGB$%^&UJB^YH*UHYJM"; // use stronger key in prod

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getUser_role())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return extractUsername(token).equals(userDetails.getUsername());
    }
}
