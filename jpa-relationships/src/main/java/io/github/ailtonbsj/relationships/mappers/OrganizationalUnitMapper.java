package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitSimpleDTO;
import io.github.ailtonbsj.relationships.models.OrganizationalUnit;
import io.github.ailtonbsj.relationships.repositories.OrganizationalUnitRepository;

@Mapper
public abstract class OrganizationalUnitMapper {

    @Autowired
    private OrganizationalUnitRepository repository;

    @Mapping(target="users", ignore=true)
    @Mapping(target="parentUnit", expression="java(findParent(dto.getParentUnitId()))")
    public abstract OrganizationalUnit toModel(OrganizationalUnitSimpleDTO dto);

    protected OrganizationalUnit findParent(Long id) {
        if(id == null) return null;
        return repository.findById(id).get();
    }

    @Mapping(target="parentUnitId", source="parentUnit.id")
    public abstract OrganizationalUnitSimpleDTO toSimpleDto(OrganizationalUnit ou);

    public abstract  OrganizationalUnitDTO toDto(OrganizationalUnit ou);

    public abstract List<OrganizationalUnitDTO> toDto(List<OrganizationalUnit> ous);

}
