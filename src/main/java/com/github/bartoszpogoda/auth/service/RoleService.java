package com.github.bartoszpogoda.auth.service;

import com.github.bartoszpogoda.auth.repository.RoleRepository;
import com.github.bartoszpogoda.auth.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

}
