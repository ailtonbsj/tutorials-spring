package io.github.ailtonbsj.mybatis.role;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
                .map(entity -> dao.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if(dao.findById(id).isEmpty()) return false;
        dao.deleteById(id);
        return true;
    }
}
