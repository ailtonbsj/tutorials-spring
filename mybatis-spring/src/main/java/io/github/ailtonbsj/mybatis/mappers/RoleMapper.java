package io.github.ailtonbsj.mybatis.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.mybatis.dtos.RoleDTO;
import io.github.ailtonbsj.mybatis.models.Role;


@Mapper
public interface RoleMapper {

    @Mapping(target="users", ignore=true)
    Role toModel(RoleDTO dto);

    RoleDTO toDto(Role model);

    List<RoleDTO> toDto(List<Role> model);

}
