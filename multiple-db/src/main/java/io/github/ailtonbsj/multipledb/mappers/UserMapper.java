package io.github.ailtonbsj.multipledb.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.github.ailtonbsj.multipledb.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.multipledb.dtos.UserDTO;
import io.github.ailtonbsj.multipledb.models.ActiveSession;
import io.github.ailtonbsj.multipledb.models.User;
import io.github.ailtonbsj.multipledb.models.UserPK;
import io.github.ailtonbsj.multipledb.utils.MapperUtils;
import io.github.ailtonbsj.multipledb.utils.Utils;

@Mapper(componentModel = "spring", imports = {MapperUtils.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /********************************** to Model *********************************/

    @Mapping(target = "password", expression = "java(MapperUtils.encryptPassword(dto.getPassword()))")
    User toModel(UserDTO dto);

    // default String removeBlank(String prop) {
    // return StringUtils.hasLength(prop) ? prop : null;
    // }

    /********************************** to Dto  *********************************/

    @Mapping(target = "id", expression = "java(toId(model))")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "activeSessionsId", source = "activeSessions")
    @Mapping(target = "rolesId", ignore = true)
    UserDTO toDto(User model);

    List<UserDTO> toDto(List<User> model);

    default String toId(User model) {
        var pk = new UserPK(model.getUsername(), model.getCreatedAt());
        return Utils.idToString(pk);
    }

    default List<String> toActiveSessionsId(List<ActiveSession> activeSessions) {
        if(activeSessions == null) return null;
        return activeSessions.stream().map(ActiveSessionMapper.INSTANCE::toId).toList();
    }

    default List<ActiveSessionDTO> toActiveSessions(List<ActiveSession> activeSessions) {
        if(activeSessions == null) return null;
        return activeSessions.stream().map(ActiveSessionMapper.INSTANCE::toDto).toList();
    }

}
