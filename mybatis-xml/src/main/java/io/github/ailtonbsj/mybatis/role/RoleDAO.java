package io.github.ailtonbsj.mybatis.role;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RoleDAO {

    @Select("select * from roles")
    List<Role> findAll();

    @Select("select * from roles where id = #{id}")
    Optional<Role> findById(Long id);

    @Delete("delete from roles where id = #{id}")
    void deleteById(Long id);

    @Insert("insert into roles (name) values (#{name})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Role model);

    @Update("update roles set name = #{name} where id = #{id}")
    void update(Role model);

    default Role save(Role model) {
        if (findById(model.getId()).isPresent())
            update(model);
        else
            insert(model);
        return findById(model.getId()).get();
    }

}
