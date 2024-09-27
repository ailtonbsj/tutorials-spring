package io.github.ailtonbsj.relationships.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationalUnitSimpleDTO {
    Long id;

    @NotBlank
    String name;

    Long parentUnitId;
}
