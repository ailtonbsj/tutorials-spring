package io.github.ailtonbsj.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.relationships.dtos.UserDTO;
import io.github.ailtonbsj.relationships.mappers.UserMapper;
import io.github.ailtonbsj.relationships.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public UserDTO create(UserDTO dto) {
        dto.setId(null);
        dto.setCreatedAt(null);
        var saved = repository.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<UserDTO> show(Long id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<UserDTO> update(Long id, UserDTO dto) {
        dto.setId(id);
        return repository.findById(id)
                .stream()
                .peek(entity -> dto.setCreatedAt(entity.getCreatedAt()))
                .map(entity -> repository.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated))
                .findAny();
    }

    public boolean destroy(Long id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }
}
