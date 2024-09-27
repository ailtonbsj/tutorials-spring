package io.github.ailtonbsj.relationships.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.models.OrganizationalUnit;
import io.github.ailtonbsj.relationships.repositories.OrganizationalUnitRepository;

@Mapper
public abstract class OrganizationalUnitMapper {

    @Autowired
    private OrganizationalUnitRepository repository;

    @Mapping(target="users", ignore=true)
    @Mapping(target="parentUnit", expression="java(findParent(dto.getParentUnit()))")
    public abstract OrganizationalUnit toModel(OrganizationalUnitDTO dto);

    protected OrganizationalUnit findParent(Long id) {
        if(id == null) return null;
        return repository.findById(id).get();
    }

}
