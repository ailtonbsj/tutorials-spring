package io.github.ailtonbsj.relationships.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import io.github.ailtonbsj.relationships.dtos.UserDTO;
import io.github.ailtonbsj.relationships.models.OrganizationalUnit;
import io.github.ailtonbsj.relationships.models.Profile;
import io.github.ailtonbsj.relationships.models.Role;
import io.github.ailtonbsj.relationships.models.User;


@Mapper
public interface UserMapper {

    @Mapping(target="password", expression="java(MapperUtils.encryptPassword(dto.getPassword()))")
    @Mapping(target="createdAt", expression="java(MapperUtils.dateTimeNowIfNull(dto.getCreatedAt()))")
    @Mapping(target="department", source="departmentId")
    @Mapping(target="profile", source="profileId")
    @Mapping(target="roles", source="rolesId")
    @Mapping(target="sessions", ignore=true)
    User toModel(UserDTO dto);

    default OrganizationalUnit toOranizationalUnit(Long ouId) {
        if(ouId == null) return null;
        var ou = new OrganizationalUnit();
        ou.setId(ouId);
        return ou;
    }

    default Profile toProfile(Long profileId) {
        if(profileId == null) return null;
        var profile = new Profile();
        profile.setId(profileId);
        return profile;
    }

    default List<Role> toRoles(List<Long> rolesId) {
        if(rolesId == null) return null;
        return rolesId.stream().map(roleId -> {
            var role = new Role();
            role.setId(roleId);
            return role;
        }).toList();
    }

    @Mapping(target="password", ignore=true)
    @Mapping(target="profileId", source="profile.id")
    @Mapping(target="departmentId", source="department.id")
    @Mapping(target="rolesId", source="roles")
    UserDTO toDto(User model);

    default List<Long> toRoleIds(List<Role> roles) {
        if(roles == null) return null;
        return roles.stream().map(Role::getId).toList();
    }

    List<UserDTO> toDto(List<User> model);
}
