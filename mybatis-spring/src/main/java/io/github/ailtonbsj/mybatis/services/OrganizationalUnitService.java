package io.github.ailtonbsj.mybatis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.mybatis.daos.OrganizationalUnitDAO;
import io.github.ailtonbsj.mybatis.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.mybatis.mappers.OrganizationalUnitMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationalUnitService {

    private final OrganizationalUnitDAO dao;
    private final OrganizationalUnitMapper mapper;

    public List<OrganizationalUnitDTO> index() {
        return mapper.toDto(dao.findAll());
    }

    public OrganizationalUnitDTO create(OrganizationalUnitDTO dto) {
        dto.setId(null);
        var saved = dao.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<OrganizationalUnitDTO> show(Long id) {
        var ok = dao.findById(id);
        return ok.map(mapper::toDto);
    }

    public Optional<OrganizationalUnitDTO> update(Long id, OrganizationalUnitDTO dto) {
        dto.setId(id);
        return dao.findById(id)
                .map(ent -> dao.save(mapper.toModel(dto)))
                .map(ent -> mapper.toDto(ent));
    }

    public boolean destroy(Long id) {
        if(dao.findById(id).isEmpty()) return false;
        dao.deleteById(id);
        return true;
    }
}
