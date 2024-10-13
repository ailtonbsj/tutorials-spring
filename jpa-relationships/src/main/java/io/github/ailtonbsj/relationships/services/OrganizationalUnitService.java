package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.dtos.OrganizationalUnitDTO;
import io.github.ailtonbsj.relationships.mappers.OrganizationalUnitMapper;
import io.github.ailtonbsj.relationships.repositories.OrganizationalUnitRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationalUnitService {

    private final OrganizationalUnitRepository repository;
    private final OrganizationalUnitMapper mapper;

    public List<OrganizationalUnitDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public Optional<OrganizationalUnitDTO> create(OrganizationalUnitDTO dto) {
        var res = repository.findById(dto.getId());
        if(res.isPresent()) return Optional.empty();
        dto.setId(null);
        var saved = repository.save(mapper.toModel(dto));
        return Optional.of(mapper.toDto(saved));
    }

    public Optional<OrganizationalUnitDTO> show(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<OrganizationalUnitDTO> update(Long id, OrganizationalUnitDTO dto) {
        dto.setId(id);
        return repository.findById(id)
                .map(ent -> repository.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if(repository.findById(id).isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }
}
