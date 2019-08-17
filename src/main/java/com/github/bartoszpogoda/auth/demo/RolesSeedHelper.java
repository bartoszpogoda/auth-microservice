package com.github.bartoszpogoda.auth.demo;

import com.github.bartoszpogoda.auth.entity.Activity;
import com.github.bartoszpogoda.auth.entity.Permission;
import com.github.bartoszpogoda.auth.entity.Role;
import com.github.bartoszpogoda.auth.entity.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RolesSeedHelper {

    private RolesSeedHelper() { }

    public static Role adminRole() {
        Activity allowAllActivity = Activity.builder().method("ALL").urlPattern("/**").name("allowAll").build();

        Permission permission = Permission.builder()
                .name("adminPermission")
                .activities(Collections.singletonList(allowAllActivity))
                .build();

        return Role.builder().name("ROLE_ADMIN").permissions(Collections.singletonList(permission)).build();
    }

    public static Role testRole() {
        Activity getTestActivity = Activity.builder().method("GET").urlPattern("/test").name("getTest").build();
        Activity postTestActivity = Activity.builder().method("POST").urlPattern("/test").name("postTest").build();

        Permission permission = Permission.builder()
                .name("testPermission")
                .activities(Lists.newArrayList(getTestActivity, postTestActivity))
                .build();

        return Role.builder().name("ROLE_TEST").permissions(Collections.singletonList(permission)).build();
    }

    public static Set<Role> boxedInSet(Role role) {
        return Sets.newHashSet(role);
    }
}
