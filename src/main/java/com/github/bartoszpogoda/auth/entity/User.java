package com.github.bartoszpogoda.auth.entity;

import lombok.*;


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

    private String encodedPassword;

}
