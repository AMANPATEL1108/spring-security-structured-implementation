package com.structuredsprindsecurity.springsecuritystructured.api.serviceImpl;


import com.structuredsprindsecurity.springsecuritystructured.api.model.User;
import com.structuredsprindsecurity.springsecuritystructured.api.repository.UserRepository;
import com.structuredsprindsecurity.springsecuritystructured.api.service.AuthUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthUserDetailsServiceImplementation implements UserDetailsService, AuthUserDetailsService {

    @Autowired
    private com.structuredsprindsecurity.springsecuritystructured.api.repository.UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getUser_password(),
                List.of(new SimpleGrantedAuthority(user.getUser_role()))
        );
    }
}
