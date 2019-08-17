package com.github.bartoszpogoda.auth.demo;

import com.github.bartoszpogoda.auth.entity.Activity;
import com.github.bartoszpogoda.auth.entity.Permission;
import com.github.bartoszpogoda.auth.entity.Role;
import com.github.bartoszpogoda.auth.entity.User;
import com.github.bartoszpogoda.auth.repository.UserRepository;
import com.google.common.collect.Lists;
import lombok.extern.java.Log;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Profile("seed")
@Component
@Log
public class DataSeed implements InitializingBean {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public DataSeed(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() {
        User adminUser = User.builder()
                .email("admin@admin.com")
                .encodedPassword(this.passwordEncoder.encode("admin123"))
                .roles(RolesSeedHelper.boxedInSet(RolesSeedHelper.adminRole()))
                .build();

        User testUser = User.builder()
                .email("adam.kowalski@onet.pl")
                .encodedPassword(this.passwordEncoder.encode("pass123"))
                .roles(RolesSeedHelper.boxedInSet(RolesSeedHelper.testRole()))
                .build();

        this.userRepository.save(adminUser);
        this.userRepository.save(testUser);
    }

}
