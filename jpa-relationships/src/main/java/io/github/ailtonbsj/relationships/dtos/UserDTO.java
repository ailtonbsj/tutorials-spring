package io.github.ailtonbsj.relationships.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank
    String username;

    @NotBlank
    String password;

    Boolean isActive;

    LocalDateTime createdAt;

    Long profileId;
    
    @NotNull
    Long departmentId;
    
    Set<Long> sesssionsId;
    
    Set<Long> rolesId;
}
