package io.github.ailtonbsj.mybatis.dtos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    Long id;

    @NotBlank
    String username;

    @NotBlank
    String password;

    @NotNull
    Boolean isActive;

    LocalDateTime createdAt;

    Long profileId;

    @NotNull
    Long departmentId;

    List<Long> activeSessionsId;

    List<Long> rolesId;
}
