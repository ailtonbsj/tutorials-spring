package io.github.ailtonbsj.mybatis.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDTO {
    Long id;

    @NotBlank
    String name;
}
