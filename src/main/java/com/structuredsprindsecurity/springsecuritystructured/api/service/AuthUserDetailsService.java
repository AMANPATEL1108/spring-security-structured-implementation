package com.structuredsprindsecurity.springsecuritystructured.api.service;
import org.springframework.security.core.userdetails.UserDetails;
public interface AuthUserDetailsService {
    UserDetails loadUserByUsername(String username);
}