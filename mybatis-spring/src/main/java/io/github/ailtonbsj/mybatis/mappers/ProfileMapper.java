package io.github.ailtonbsj.mybatis.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import io.github.ailtonbsj.mybatis.dtos.ProfileDTO;
import io.github.ailtonbsj.mybatis.models.Profile;


@Mapper
public interface ProfileMapper {

    Profile toModel(ProfileDTO dto);

    ProfileDTO toDto(Profile model);

    List<ProfileDTO> toDto(List<Profile> model);

}
