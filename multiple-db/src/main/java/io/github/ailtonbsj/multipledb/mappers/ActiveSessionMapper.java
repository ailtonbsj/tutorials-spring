package io.github.ailtonbsj.multipledb.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.multipledb.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.multipledb.models.ActiveSession;
import io.github.ailtonbsj.multipledb.models.ActiveSessionPK;
import io.github.ailtonbsj.multipledb.models.UserPK;
import io.github.ailtonbsj.multipledb.utils.Utils;

@Mapper(componentModel = "spring", imports = { Utils.class, UserPK.class, ActiveSessionPK.class })
public interface ActiveSessionMapper {

    @Mapping(target = "id.userId", source = "userId")
    @Mapping(target = "id.userCreatedAt", source = "userCreatedAt")
    @Mapping(target = "id.device", source = "device")
    ActiveSession toModel(ActiveSessionDTO dto);

    // default String removeBlank(String prop) {
    // return StringUtils.hasLength(prop) ? prop : null;
    // }

    // default User toUser(Long id) {
    // return MapperUtils.toEntity(id, User.class);
    // }

    @Mapping(target = "id", expression = "java(Utils.idToString(model.getId()))")
    @Mapping(target = "userId", source = "id.userId")
    @Mapping(target = "userCreatedAt", source = "id.userCreatedAt")
    @Mapping(target = "device", source = "id.device")
    ActiveSessionDTO toDto(ActiveSession model);

    List<ActiveSessionDTO> toDto(List<ActiveSession> model);
}
