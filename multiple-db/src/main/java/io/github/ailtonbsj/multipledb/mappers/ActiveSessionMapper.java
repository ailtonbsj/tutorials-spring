package io.github.ailtonbsj.multipledb.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.github.ailtonbsj.multipledb.dtos.ActiveSessionDTO;
import io.github.ailtonbsj.multipledb.models.ActiveSession;
import io.github.ailtonbsj.multipledb.models.ActiveSessionPK;
import io.github.ailtonbsj.multipledb.utils.Utils;

@Mapper(componentModel = "spring")
public interface ActiveSessionMapper {

    ActiveSessionMapper INSTANCE = Mappers.getMapper(ActiveSessionMapper.class);

    /********************************** to Model *********************************/

    ActiveSession toModel(ActiveSessionDTO dto);

    // default String removeBlank(String prop) {
    // return StringUtils.hasLength(prop) ? prop : null;
    // }

    // default User toUser(Long id) {
    // return MapperUtils.toEntity(id, User.class);
    // }

    /********************************** to Dto  *********************************/

    @Mapping(target = "id", expression = "java(toId(model))")
    ActiveSessionDTO toDto(ActiveSession model);

    default String toId(ActiveSession model) {
        var pk = new ActiveSessionPK(model.getUserId(), model.getUserCreatedAt(), model.getDevice());
        return Utils.idToString(pk);
    }

    List<ActiveSessionDTO> toDto(List<ActiveSession> model);
}
