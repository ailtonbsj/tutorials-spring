package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.relationships.dtos.RoleDTO;
import io.github.ailtonbsj.relationships.models.Role;


@Mapper
public interface RoleMapper {

    @Mapping(target="users", ignore=true)
    Role toModel(RoleDTO dto);

    RoleDTO toDto(Role model);

    List<RoleDTO> toDto(List<Role> model);

}
