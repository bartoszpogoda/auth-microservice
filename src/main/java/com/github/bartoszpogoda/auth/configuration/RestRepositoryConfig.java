package com.github.bartoszpogoda.auth.configuration;

import com.github.bartoszpogoda.auth.entity.Activity;
import com.github.bartoszpogoda.auth.entity.Permission;
import com.github.bartoszpogoda.auth.entity.Role;
import com.github.bartoszpogoda.auth.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RestRepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setBasePath("/manage")
                .setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED)
                .exposeIdsFor(Activity.class, Permission.class, Role.class, User.class);
    }
}
