package io.github.ailtonbsj.mybatis.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.ailtonbsj.mybatis.daos.ActiveSessionDAO;
import io.github.ailtonbsj.mybatis.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.mybatis.mappers.ActiveSessionMapper;
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
        return dao.findById(id)
                .map(entity -> {
                    dto.setId(id);
                    dao.save(mapper.toModel(dto));
                    return mapper.toDto(entity);
                });
    }

    public boolean destroy(Long id) {
        if (dao.findById(id).isEmpty())
            return false;
        dao.deleteById(id);
        return true;
    }
}
