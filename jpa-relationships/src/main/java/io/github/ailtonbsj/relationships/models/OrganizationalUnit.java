package io.github.ailtonbsj.relationships.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class OrganizationalUnit {
    @Id
    Long id;

    String name;
    
    OrganizationalUnit parent;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    Set<User> users = new HashSet<>();
}
