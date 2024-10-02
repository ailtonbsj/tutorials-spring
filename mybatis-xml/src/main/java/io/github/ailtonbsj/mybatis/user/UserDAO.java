package io.github.ailtonbsj.mybatis.user;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import io.github.ailtonbsj.mybatis.role.Role;
import jakarta.transaction.Transactional;

@Mapper
public interface UserDAO {

    final String OU_BY_ID = "io.github.ailtonbsj.mybatis.organizationalunit.OrganizationalUnitDAO.findSimpleById";
    final String PROFILE_BY_ID = "io.github.ailtonbsj.mybatis.profile.ProfileDAO.findById";
    final String SESSION_BY_USER = "io.github.ailtonbsj.mybatis.activesession.ActiveSessionDAO.findByUserId";

    @Select("select u.*, u.id user_id, u.id id_session from users u where u.id = #{id}")
    @Results(id = "userResultMap", value = {
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "department", column = "department_id", many = @Many(select = OU_BY_ID, fetchType = FetchType.LAZY)),
            @Result(property = "profile", column = "profile_id", one = @One(select = PROFILE_BY_ID, fetchType = FetchType.LAZY)),
            @Result(property = "roles", column = "user_id", many = @Many(select = "permissionsByUserId", fetchType = FetchType.LAZY)),
            @Result(property = "activeSessions", column = "id_session", many = @Many(select = SESSION_BY_USER, fetchType = FetchType.LAZY))
    })
    Optional<User> findById(Long id);

    @Select("select u.*, u.id user_id, u.id id_session from users u")
    @ResultMap("userResultMap")
    List<User> findAll();

    @Transactional
    default void deleteById(Long id) {
        deletePermsByUserId(id);
        deleteUserById(id);
    }

    @Insert("""
            insert into users (
                username, password, is_active, department_id, profile_id, created_at
            ) values (
                #{username}, #{password}, #{isActive}, #{department.id}, #{profile.id}, #{createdAt}
            )
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(User model);

    @Update("""
            update users set
                username = #{username}, password = #{password}, is_active = #{isActive},
                department_id = #{department.id}, profile_id = #{profile.id}, created_at = #{createdAt}
            where id = #{id}
            """)
    void update(User model);

    @Delete("delete from users where id = #{id}")
    void deleteUserById(Long id);

    @Insert("insert into permissions (user_id, role_id) values (#{user.id}, #{role.id})")
    void insertPermission(User user, Role role);

    @Select("""
            select r.* from permissions p
            left join roles r on p.role_id = r.id
            where user_id = #{id}
            """)
    List<Role> permissionsByUserId(Long id);

    @Delete("delete from permissions where user_id = #{id}")
    void deletePermsByUserId(Long id);

    @Transactional
    default User save(User model) {
        if (findById(model.getId()).isPresent()) {
            deletePermsByUserId(model.getId());
            update(model);
        } else {
            insert(model);
        }

        var roles = model.getRoles();
        if (roles != null)
            roles.forEach(role -> insertPermission(model, role));

        return findById(model.getId()).get();
    }

    @Select("select u.*, u.id user_id, u.id id_session from users u where u.department_id = #{ouId}")
    @ResultMap("userResultMap")
    List<User> findByOU(Long ouId);

}
