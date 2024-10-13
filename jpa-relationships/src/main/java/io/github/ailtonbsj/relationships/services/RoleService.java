package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<RoleDTO> filter(RoleDTO sample, Pageable pageable) {
        var model = mapper.toModel(sample);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        var example = Example.of(model, matcher);
        var result = repository.findAll(example, pageable);
        return result.map(mapper::toDto);
    }

    public List<RoleDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public RoleDTO create(RoleDTO dto) {
        // var res = repository.findById(dto.getId());
        // if(res.isPresent()) throw new DataIntegrityViolationException("Já existem registros com mesmos valores na base.");
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
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }
}
