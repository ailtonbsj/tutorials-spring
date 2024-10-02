package io.github.ailtonbsj.mybatis.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleDTO {
    Long id;

    @NotBlank
    String name;
}
