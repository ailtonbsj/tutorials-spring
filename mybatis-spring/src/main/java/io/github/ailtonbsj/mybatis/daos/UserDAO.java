package io.github.ailtonbsj.mybatis.daos;

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

import io.github.ailtonbsj.mybatis.models.Role;
import io.github.ailtonbsj.mybatis.models.User;
import jakarta.transaction.Transactional;

@Mapper
public interface UserDAO {

    @Select("select u.*, s.id userId from users u left join users s on u.id = s.id where u.id = #{id}")
    @Results(id = "userResultMap", value = {
            @Result(property = "isActive", column = "is_active"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "department", column = "department_id",
                many = @Many(select = "io.github.ailtonbsj.mybatis.daos.OrganizationalUnitDAO.findSimpleById", fetchType = FetchType.LAZY)),
            @Result(property = "profile", column = "profile_id",
                one = @One(select = "io.github.ailtonbsj.mybatis.daos.ProfileDAO.findById", fetchType = FetchType.LAZY)),
            @Result(property = "roles", column = "userId", javaType = List.class,
                many = @Many(select = "permissionsByUserId", fetchType = FetchType.LAZY))
    })
    Optional<User> findById(Long id);

    @Select("select u.*, s.id userId from users u left join users s on u.id = s.id")
    @ResultMap("userResultMap")
    List<User> findAll();

    @Transactional
    default void deleteById(Long id) {
        deletePermsByUserId(id);
        deleteUserById(id);
    }

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

    @Insert("""
            insert into users (
                id, username, password, is_active, department_id, profile_id, created_at
            ) values (
                #{id}, #{username}, #{password}, #{isActive}, #{department.id}, #{profile.id}, #{createdAt}
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

}
