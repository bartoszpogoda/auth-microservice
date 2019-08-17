package com.github.bartoszpogoda.auth.service;

import com.github.bartoszpogoda.auth.entity.Role;
import com.github.bartoszpogoda.auth.entity.User;
import com.github.bartoszpogoda.auth.error.impl.DefaultRoleForUserDoesntExistError;
import com.github.bartoszpogoda.auth.repository.UserRepository;
import com.github.bartoszpogoda.auth.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private String defaultUserRole;

    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       PasswordEncoder passwordEncoder,
                       @Value("${auth.defaultUserRole}") String defaultUserRole) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.defaultUserRole = defaultUserRole;
    }

    public Optional<User> findById(long id) { return this.userRepository.findById(id); }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> register(RegistrationRequest request) {
        Role defaultRole = this.roleService.findByName(defaultUserRole)
                .orElseThrow(DefaultRoleForUserDoesntExistError::new);

        User newUser = User.builder()
                .email(request.getEmail())
                .encodedPassword(this.passwordEncoder.encode(request.getPlainPassword()))
                .roles(Collections.singleton(defaultRole))
                .build();

        return Optional.of(this.userRepository.save(newUser));
    }

    public boolean userWithEmailExists(String email) {
        return this.userRepository.existsByEmail(email);
    }

}
