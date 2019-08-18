package com.github.bartoszpogoda.auth.service;

import com.github.bartoszpogoda.auth.rest.RoleRestRepository;
import com.github.bartoszpogoda.auth.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRestRepository roleRepository;

    public RoleService(RoleRestRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

}
