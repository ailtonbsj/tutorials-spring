package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.models.OrganizationalUnit;


@Mapper
public interface OrganizationalUnitMapper {

    @Mapping(target="users", ignore=true)
    @Mapping(target="parentUnit", expression="java(setParentUnit(dto.getParentUnitId()))")
    OrganizationalUnit toModel(OrganizationalUnitDTO dto);

    default OrganizationalUnit setParentUnit(Long parentUnitId) {
        if(parentUnitId == null) return null;
        var parent = new OrganizationalUnit();
        parent.setId(parentUnitId);
        return parent;
    }

    @Mapping(target="parentUnitId", source="parentUnit.id")
    OrganizationalUnitDTO toDto(OrganizationalUnit model);

    List<OrganizationalUnitDTO> toDto(List<OrganizationalUnit> model);

}
