package com.github.bartoszpogoda.auth.rest;

import com.github.bartoszpogoda.auth.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource
public interface RoleRestRepository extends CrudRepository<Role, String> {

    @RestResource(exported = false)
    Optional<Role> findByName(String name);
}
