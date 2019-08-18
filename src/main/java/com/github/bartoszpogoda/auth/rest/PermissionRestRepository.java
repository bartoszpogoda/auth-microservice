package com.github.bartoszpogoda.auth.rest;

import com.github.bartoszpogoda.auth.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PermissionRestRepository extends CrudRepository<Permission, String> {

}
