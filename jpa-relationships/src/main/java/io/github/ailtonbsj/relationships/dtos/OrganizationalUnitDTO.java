package io.github.ailtonbsj.relationships.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationalUnitDTO {
    Long id;

    @NotBlank
    String name;

    Long parentUnit;
}
