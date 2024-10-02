package io.github.ailtonbsj.mybatis.activesession;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActiveSessionService {

    private final ActiveSessionDAO dao;
    private final ActiveSessionMapper mapper;

    public List<ActiveSessionDTO> index() {
        return mapper.toDto(dao.findAll());
    }

    public ActiveSessionDTO create(ActiveSessionDTO dto) {
        dto.setId(null);
        var saved = dao.save(mapper.toModel(dto));
        return mapper.toDto(saved);
    }

    public Optional<ActiveSessionDTO> show(Long id) {
        return dao.findById(id).map(mapper::toDto);
    }

    public Optional<ActiveSessionDTO> update(Long id, ActiveSessionDTO dto) {
        dto.setId(id);
        return dao.findById(id)
                .map(entity -> dao.save(mapper.toModel(dto)))
                .map(updated -> mapper.toDto(updated));
    }

    public boolean destroy(Long id) {
        if (dao.findById(id).isEmpty())
            return false;
        dao.deleteById(id);
        return true;
    }
}
