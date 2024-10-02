package io.github.ailtonbsj.mybatis.daos;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import io.github.ailtonbsj.mybatis.models.OrganizationalUnit;

@Mapper
public interface OrganizationalUnitDAO {

    @Select("select * from organizational_unit")
    @Results({
            @Result(property = "parentUnit", column = "parent_unit", many = @Many(select = "findSimpleById", fetchType = FetchType.LAZY))
    })
    List<OrganizationalUnit> findAll();

    @Select("select * from organizational_unit where id = #{id}")
    @Results({
            @Result(property = "parentUnit", column = "parent_unit", many = @Many(select = "findSimpleById", fetchType = FetchType.LAZY))
    })
    Optional<OrganizationalUnit> findById(Long id);

    @Select("select * from organizational_unit where id = #{id}")
    Optional<OrganizationalUnit> findSimpleById(Long id);

    @Delete("delete from organizational_unit where id = #{id}")
    void deleteById(Long id);

    @Insert("""
            insert into organizational_unit (
                id, name, parent_unit
            ) values (
                #{id}, #{name}, #{parentUnit.id}
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
