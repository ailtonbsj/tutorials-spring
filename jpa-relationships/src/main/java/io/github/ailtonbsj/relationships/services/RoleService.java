package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.dtos.RoleDTO;
import io.github.ailtonbsj.relationships.mappers.RoleMapper;
import io.github.ailtonbsj.relationships.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    public List<RoleDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public RoleDTO create(RoleDTO dto) {
        dto.setId(null);
        var saved = repository.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<RoleDTO> show(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<RoleDTO> update(Long id, RoleDTO dto) {
        dto.setId(id);
        return repository.findById(id)
                .map(ent -> repository.save(mapper.toModel(dto)))
                .map(ent -> mapper.toDto(ent));
    }

    public boolean destroy(Long id) {
        if(repository.findById(id).isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }
}
