package io.github.ailtonbsj.relationships.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class OrganizationalUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentUnit")
    OrganizationalUnit parentUnit;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    List<User> users;
}
