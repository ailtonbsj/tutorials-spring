package io.github.ailtonbsj.relationships.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationalUnitDTO {
    Long id;

    @NotBlank
    String name;

    Long parentUnitId;

    List<Long> usersId;
}
