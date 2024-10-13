package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.dtos.ProfileDTO;
import io.github.ailtonbsj.relationships.mappers.ProfileMapper;
import io.github.ailtonbsj.relationships.models.Profile;
import io.github.ailtonbsj.relationships.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    public Page<ProfileDTO> filter(ProfileDTO sample, Pageable pageable) {
        Profile model = mapper.toModel(sample);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<Profile> example = Example.of(model, matcher);
        Page<Profile> result = repository.findAll(example, pageable);
        return result.map(mapper::toDto);
    }

    public List<ProfileDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public ProfileDTO create(ProfileDTO dto) {
        // var res = repository.findById(dto.getId());
        // if(res.isPresent()) throw new DataIntegrityViolationException("Já existem registros com mesmos valores na base.");
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
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }

}
