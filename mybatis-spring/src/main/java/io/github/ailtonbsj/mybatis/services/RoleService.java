package io.github.ailtonbsj.mybatis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.mybatis.dtos.RoleDTO;
import io.github.ailtonbsj.mybatis.mappers.RoleMapper;
import io.github.ailtonbsj.mybatis.daos.RoleDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleDAO dao;
    private final RoleMapper mapper;

    public List<RoleDTO> index() {
        return mapper.toDto(dao.findAll());
    }

    public RoleDTO create(RoleDTO dto) {
        dto.setId(null);
        var saved = dao.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<RoleDTO> show(Long id) {
        return dao.findById(id).map(mapper::toDto);
    }

    public Optional<RoleDTO> update(Long id, RoleDTO dto) {
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
