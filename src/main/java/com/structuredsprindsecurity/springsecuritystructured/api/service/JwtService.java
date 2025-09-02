package com.structuredsprindsecurity.springsecuritystructured.api.service;

import com.structuredsprindsecurity.springsecuritystructured.api.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String generateToken(User user);
    String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
}
