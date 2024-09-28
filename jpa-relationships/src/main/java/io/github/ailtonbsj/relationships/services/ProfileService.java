package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.dtos.ProfileDTO;
import io.github.ailtonbsj.relationships.mappers.ProfileMapper;
import io.github.ailtonbsj.relationships.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    public List<ProfileDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public ProfileDTO create(ProfileDTO dto) {
        dto.setId(null);
        var saved = repository.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<ProfileDTO> show(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<ProfileDTO> update(Long id, ProfileDTO dto) {
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
