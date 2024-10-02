package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.relationships.mappers.ActiveSessionMapper;
import io.github.ailtonbsj.relationships.repositories.ActiveSessionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActiveSessionService {

    private final ActiveSessionRepository repository;
    private final ActiveSessionMapper mapper;

    public List<ActiveSessionDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public ActiveSessionDTO create(ActiveSessionDTO dto) {
        dto.setId(null);
        var saved = repository.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<ActiveSessionDTO> show(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<ActiveSessionDTO> update(Long id, ActiveSessionDTO dto) {
        dto.setId(id);
        return repository.findById(id)
                .map(entity -> repository.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }
}
