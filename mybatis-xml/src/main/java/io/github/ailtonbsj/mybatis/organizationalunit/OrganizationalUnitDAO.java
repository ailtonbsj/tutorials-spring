package io.github.ailtonbsj.mybatis.organizationalunit;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface OrganizationalUnitDAO {

    final String USER_BY_OU = "io.github.ailtonbsj.mybatis.user.UserDAO.findByOU";

    @Select("select o.*, o.id ou_id from organizational_unit o where o.id = #{id}")
    @Results(id = "ouResultMap", value = {
            @Result(property = "parentUnit", column = "parent_unit", many = @Many(select = "findSimpleById", fetchType = FetchType.LAZY)),
            @Result(property = "users", column = "ou_id", many = @Many(select = USER_BY_OU, fetchType = FetchType.LAZY))
    })
    Optional<OrganizationalUnit> findById(Long id);

    @Select("select o.*, o.id ou_id from organizational_unit o")
    @ResultMap("ouResultMap")
    List<OrganizationalUnit> findAll();

    @Select("select * from organizational_unit where id = #{id}")
    Optional<OrganizationalUnit> findSimpleById(Long id);

    @Delete("delete from organizational_unit where id = #{id}")
    void deleteById(Long id);

    @Insert("""
            insert into organizational_unit (
                name, parent_unit
            ) values (
                #{name}, #{parentUnit.id}
            )
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(OrganizationalUnit model);

    @Update("""
            update organizational_unit set
                name = #{name}, parent_unit = #{parentUnit.id}
            where id = #{id}
            """)
    void update(OrganizationalUnit model);

    default OrganizationalUnit save(OrganizationalUnit model) {
        if (findById(model.getId()).isPresent())
            update(model);
        else
            insert(model);
        return findById(model.getId()).get();
    }

}
