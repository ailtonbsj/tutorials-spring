package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.relationships.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.relationships.models.ActiveSession;
import io.github.ailtonbsj.relationships.models.User;

@Mapper
public interface ActiveSessionMapper {
    
    @Mapping(target="user", source="userId")
    ActiveSession toModel(ActiveSessionDTO dto);

    default User toUser(Long id) {
        return MapperUtils.toEntity(id, User.class);
    }

    @Mapping(target="userId", source="user.id")
    ActiveSessionDTO toDto(ActiveSession model);

    List<ActiveSessionDTO> toDto(List<ActiveSession> model);
}
