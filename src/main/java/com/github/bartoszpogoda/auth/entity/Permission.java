package com.github.bartoszpogoda.auth.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

    @Id
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Activity> activities;

}
