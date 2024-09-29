package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.models.OrganizationalUnit;
import io.github.ailtonbsj.relationships.models.User;


@Mapper
public interface OrganizationalUnitMapper {

    @Mapping(target="parentUnit", expression="java(setParentUnit(dto.getParentUnitId()))")
    @Mapping(target="users", ignore=true)
    OrganizationalUnit toModel(OrganizationalUnitDTO dto);

    default OrganizationalUnit setParentUnit(Long parentUnitId) {
        if(parentUnitId == null) return null;
        var parent = new OrganizationalUnit();
        parent.setId(parentUnitId);
        return parent;
    }

    @Mapping(target="parentUnitId", source="parentUnit.id")
    @Mapping(target="usersId", source="users")
    OrganizationalUnitDTO toDto(OrganizationalUnit model);

    default List<Long> toUsers(List<User> users) {
        if(users == null) return null;
        return users.stream().map(user -> user.getId()).toList();
    }

    List<OrganizationalUnitDTO> toDto(List<OrganizationalUnit> model);

}
