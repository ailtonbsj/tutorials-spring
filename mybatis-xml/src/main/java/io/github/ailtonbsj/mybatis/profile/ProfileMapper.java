package io.github.ailtonbsj.mybatis.profile;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    Profile toModel(ProfileDTO dto);

    ProfileDTO toDto(Profile model);

    List<ProfileDTO> toDto(List<Profile> model);

}
