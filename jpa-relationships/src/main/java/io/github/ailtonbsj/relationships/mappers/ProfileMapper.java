package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import io.github.ailtonbsj.relationships.dtos.ProfileDTO;
import io.github.ailtonbsj.relationships.models.Profile;


@Mapper
public interface ProfileMapper {

    Profile toModel(ProfileDTO dto);

    ProfileDTO toDto(Profile model);

    List<ProfileDTO> toDto(List<Profile> model);

}
