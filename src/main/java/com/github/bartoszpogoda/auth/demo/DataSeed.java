package com.github.bartoszpogoda.auth.demo;

import com.github.bartoszpogoda.auth.entity.Activity;
import com.github.bartoszpogoda.auth.entity.Permission;
import com.github.bartoszpogoda.auth.entity.Role;
import com.github.bartoszpogoda.auth.entity.User;
import com.github.bartoszpogoda.auth.repository.UserRepository;
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

        User user = User.builder()
                .email("adam.kowalski@onet.pl")
                .encodedPassword(this.passwordEncoder.encode("pass123"))
                .build();
        Activity activity = Activity.builder().method("GET").urlPattern("/test").name("getTest").build();
        Permission permission = Permission.builder()
                .name("testPermission")
                .activities(Collections.singletonList(activity))
                .build();

        Role role = Role.builder().name("ROLE_USER").permissions(Collections.singletonList(permission)).build();

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        user.setRoles(roleSet);

        User savedUser = this.userRepository.save(user);

        log.info(savedUser.toString());
    }


}
