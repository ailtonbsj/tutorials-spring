package io.github.ailtonbsj.relationships.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProfileDTO {
    Long id;

    @NotBlank
    String name;
    
    Long phone;
    
    LocalDate birthday;
    
    String biography;
    
    String country;
    
    Double salary;
    
    String instagram;
    
    String avatarUrl;
}
