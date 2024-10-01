package io.github.ailtonbsj.mybatis.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
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
