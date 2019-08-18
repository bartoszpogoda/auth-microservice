package com.github.bartoszpogoda.auth.rest;

import com.github.bartoszpogoda.auth.entity.Activity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ActivityRestRepository extends CrudRepository<Activity, String> {
}
