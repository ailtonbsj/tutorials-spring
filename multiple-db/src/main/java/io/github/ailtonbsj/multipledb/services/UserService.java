package io.github.ailtonbsj.multipledb.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.ailtonbsj.multipledb.dtos.UserDTO;
import io.github.ailtonbsj.multipledb.mappers.UserMapper;
import io.github.ailtonbsj.multipledb.models.User;
import io.github.ailtonbsj.multipledb.models.UserPK;
import io.github.ailtonbsj.multipledb.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Page<UserDTO> filter(UserDTO sample, Pageable pageable) {
        User model = mapper.toModel(sample);
        model.setPassword(null);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(StringMatcher.CONTAINING);
        Example<User> example = Example.of(model, matcher);
        Page<User> result = repository.findAll(example, pageable);
        return result.map(mapper::toDto);
    }

    public List<UserDTO> index() {
        return mapper.toDto(repository.findAll());
    }

    public UserDTO create(UserDTO dto) {
        // Validating
        var res = repository.findById(new UserPK(dto.getUsername(), dto.getCreatedAt()));
        if (res.isPresent())
            throw new DataIntegrityViolationException("Já existem registros com mesmos valores na base.");
        // Mapping
        var entity = mapper.toModel(dto);
        entity.getId().setCreatedAt(LocalDate.now());
        // Persisting
        var saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public Optional<UserDTO> show(UserPK id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public Optional<UserDTO> update(UserPK id, UserDTO dto) {
        // Mapping
        dto.setUsername(id.getUsername());
        dto.setCreatedAt(id.getCreatedAt());
        
        return repository.findById(id)
                .stream()
                // Mapping
                .peek(entity -> dto.setCreatedAt(entity.getId().getCreatedAt()))
                // Persisting
                .map(entity -> repository.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated))
                .findAny();
    }

    public boolean destroy(UserPK id) {
        if (repository.findById(id).isEmpty())
            return false;
        repository.deleteById(id);
        return true;
    }

}
