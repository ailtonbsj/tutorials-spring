package io.github.ailtonbsj.mybatis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.mybatis.daos.UserDAO;
import io.github.ailtonbsj.mybatis.dtos.UserDTO;
import io.github.ailtonbsj.mybatis.mappers.UserMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO dao;
    private final UserMapper mapper;

    public List<UserDTO> index() {
        return mapper.toDto(dao.findAll());
    }

    public UserDTO create(UserDTO dto) {
        dto.setId(null);
        dto.setCreatedAt(null);
        var saved = dao.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<UserDTO> show(Long id) {
        return dao.findById(id).map(mapper::toDto);
    }

    public Optional<UserDTO> update(Long id, UserDTO dto) {
        dto.setId(id);
        return dao.findById(id)
                .stream()
                .peek(entity -> dto.setCreatedAt(entity.getCreatedAt()))
                .map(entity -> dao.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated))
                .findAny();
    }

    public boolean destroy(Long id) {
        if (dao.findById(id).isEmpty())
            return false;
        dao.deleteById(id);
        return true;
    }
}
