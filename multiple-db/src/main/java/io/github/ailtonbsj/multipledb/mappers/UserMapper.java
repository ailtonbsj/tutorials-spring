package io.github.ailtonbsj.multipledb.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.multipledb.dtos.UserDTO;
import io.github.ailtonbsj.multipledb.models.User;
import io.github.ailtonbsj.multipledb.models.UserPK;
import io.github.ailtonbsj.multipledb.utils.Utils;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", expression = "java(MapperUtils.encryptPassword(dto.getPassword()))")
    User toModel(UserDTO dto);

    // default String removeBlank(String prop) {
    // return StringUtils.hasLength(prop) ? prop : null;
    // }

    @Mapping(target = "id", expression = "java(generateId(model))")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "activeSessionsId", ignore = true)
    @Mapping(target = "rolesId", ignore = true)
    UserDTO toDto(User model);

    default String generateId(User model) {
        var pk = new UserPK(model.getUsername(), model.getCreatedAt());
        return Utils.idToString(pk);
    }

    // default List<Long> toActiveSessionsId(List<ActiveSession> activeSessions) {
    // if (activeSessions == null)
    // return null;
    // return activeSessions.stream().map(ActiveSession::getId).toList();
    // }

    List<UserDTO> toDto(List<User> model);
}
