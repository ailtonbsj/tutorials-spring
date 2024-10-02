package io.github.ailtonbsj.mybatis.activesession;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.mybatis.user.User;
import io.github.ailtonbsj.mybatis.utils.MapperUtils;

@Mapper(componentModel = "spring", imports = MapperUtils.class)
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
