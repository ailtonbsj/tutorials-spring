package io.github.ailtonbsj.relationships.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {
    @Id
    Long id;

    String name;

    @ManyToMany(mappedBy = "roles")
    Set<User> users = new HashSet<>();
}
