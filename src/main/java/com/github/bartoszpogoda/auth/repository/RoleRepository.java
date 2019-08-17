package com.github.bartoszpogoda.auth.repository;

import com.github.bartoszpogoda.auth.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

    Optional<Role> findByName(String name);
}
