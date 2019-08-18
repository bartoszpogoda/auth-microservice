package com.github.bartoszpogoda.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;


import java.util.Set;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Role> roles;

    @JsonIgnore
    private String encodedPassword;

}
