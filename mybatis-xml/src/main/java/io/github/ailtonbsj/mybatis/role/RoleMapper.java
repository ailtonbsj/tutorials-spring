package io.github.ailtonbsj.mybatis.role;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target="users", ignore=true)
    Role toModel(RoleDTO dto);

    RoleDTO toDto(Role model);

    List<RoleDTO> toDto(List<Role> model);

}
