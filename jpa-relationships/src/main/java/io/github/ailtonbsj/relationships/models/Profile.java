package io.github.ailtonbsj.relationships.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    
    Long phone;
    
    LocalDate birthday;
    
    String biography;
    
    String country;
    
    Double salary;
    
    String instagram;
    
    String avatarUrl;
}
