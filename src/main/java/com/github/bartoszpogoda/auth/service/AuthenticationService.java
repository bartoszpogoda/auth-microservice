package com.github.bartoszpogoda.auth.service;

import com.github.bartoszpogoda.auth.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(User user, String plainPassword) {
        return this.passwordEncoder.matches(plainPassword, user.getEncodedPassword());
    }
}
