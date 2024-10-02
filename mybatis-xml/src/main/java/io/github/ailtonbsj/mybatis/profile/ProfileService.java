package io.github.ailtonbsj.mybatis.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
                .map(entity -> dao.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if(dao.findById(id).isEmpty()) return false;
        dao.deleteById(id);
        return true;
    }
}
