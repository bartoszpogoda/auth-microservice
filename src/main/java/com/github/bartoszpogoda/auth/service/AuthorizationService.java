package com.github.bartoszpogoda.auth.service;

import com.github.bartoszpogoda.auth.dto.AuthorizationRequest;
import com.github.bartoszpogoda.auth.entity.Activity;
import com.github.bartoszpogoda.auth.entity.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Optional;

@Service
@Log
public class AuthorizationService {

    private final PathMatcher pathMatcher;

    public AuthorizationService(@Qualifier("authPathMatcher") PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public boolean authorize(User user, AuthorizationRequest request) {
        Optional<Activity> permittingActivity = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .flatMap(permission -> permission.getActivities().stream())
                .filter(activity -> urlMatches(request, activity))
                .filter(activity -> methodMatches(request, activity))
                .findFirst();

        permittingActivity.ifPresent(activity -> {
            log.info(String.format("Authorization request %s was authorized with activity %s",
                    request.toString(), activity.toString()));
        });

        return permittingActivity.isPresent();
    }

    private boolean urlMatches(AuthorizationRequest request, Activity activity) {
        return pathMatcher.match(activity.getUrlPattern(), request.getUrl());
    }

    private boolean methodMatches(AuthorizationRequest request, Activity activity) {
        return request.getMethod().equalsIgnoreCase(activity.getMethod())
                || activity.getMethod().equalsIgnoreCase("ALL");
    }

}
