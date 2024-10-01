package io.github.ailtonbsj.mybatis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.mybatis.dtos.ProfileDTO;
import io.github.ailtonbsj.mybatis.mappers.ProfileMapper;
import io.github.ailtonbsj.mybatis.daos.ProfileDAO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileDAO dao;
    private final ProfileMapper mapper;

    public List<ProfileDTO> index() {
        return mapper.toDto(dao.findAll());
    }

    public ProfileDTO create(ProfileDTO dto) {
        dto.setId(null);
        var saved = dao.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<ProfileDTO> show(Long id) {
        return dao.findById(id).map(mapper::toDto);
    }

    public Optional<ProfileDTO> update(Long id, ProfileDTO dto) {
        dto.setId(id);
        return dao.findById(id)
                .map(ent -> dao.save(mapper.toModel(dto)))
                .map(ent -> mapper.toDto(ent));
    }

    public boolean destroy(Long id) {
        if(dao.findById(id).isEmpty()) return false;
        dao.deleteById(id);
        return true;
    }
}
