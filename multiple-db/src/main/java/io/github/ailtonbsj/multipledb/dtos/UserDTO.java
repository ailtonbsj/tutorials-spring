package io.github.ailtonbsj.multipledb.dtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {
    String id;

    @NotBlank
    String username;

    @NotBlank
    String password;

    @NotNull
    Boolean isActive;

    LocalDate createdAt;

    Long profileId;

    @NotNull
    Long departmentId;

    List<Long> activeSessionsId;

    List<Long> rolesId;
}
