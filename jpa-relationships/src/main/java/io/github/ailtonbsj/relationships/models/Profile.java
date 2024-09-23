package io.github.ailtonbsj.relationships.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profile {
    @Id
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
