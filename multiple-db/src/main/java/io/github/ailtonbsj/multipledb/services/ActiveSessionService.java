package io.github.ailtonbsj.multipledb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.ailtonbsj.multipledb.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.multipledb.mappers.ActiveSessionMapper;
import io.github.ailtonbsj.multipledb.models.ActiveSession;
import io.github.ailtonbsj.multipledb.models.ActiveSessionPK;
import io.github.ailtonbsj.multipledb.repositories.ActiveSessionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActiveSessionService {

    private final ActiveSessionRepository repository;
    private final ActiveSessionMapper mapper;

    public Page<ActiveSessionDTO> filter(ActiveSessionDTO sample, Pageable pageable) {
        ActiveSession model = mapper.toModel(sample);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        var example = Example.of(model, matcher);
        var result = repository.findAll(example, pageable);
        return result.map(mapper::toDto);
    }

    public List<ActiveSessionDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public ActiveSessionDTO create(ActiveSessionDTO dto) {
        var key = new ActiveSessionPK(dto.getUserId(), dto.getUserCreatedAt() , dto.getDevice());
        var res = repository.findById(key);
        if (res.isPresent())
            throw new DataIntegrityViolationException("Já existem registros com mesmos valores na base.");

        dto.setId(null);
        var ent = mapper.toModel(dto);
        var saved = repository.save(ent);
        return mapper.toDto(saved);
    }

    public Optional<ActiveSessionDTO> show(ActiveSessionPK id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<ActiveSessionDTO> update(ActiveSessionPK id, ActiveSessionDTO dto) {
        dto.setUserId(id.getUserId());
        dto.setUserCreatedAt(id.getUserCreatedAt());
        dto.setDevice(id.getDevice());

        return repository.findById(id)
                .map(entity -> repository.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(ActiveSessionPK id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }
}
