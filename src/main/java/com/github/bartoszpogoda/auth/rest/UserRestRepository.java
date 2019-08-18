package com.github.bartoszpogoda.auth.rest;

import com.github.bartoszpogoda.auth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRestRepository extends CrudRepository<User, Long> {

    @RestResource(exported = false)
    boolean existsByEmail(String email);

    @RestResource(exported = false)
    Optional<User> findByEmail(String email);

}
