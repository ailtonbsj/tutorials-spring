package io.github.ailtonbsj.mybatis.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import io.github.ailtonbsj.mybatis.models.User;

@Mapper
public interface UserDAO {

    @Insert("insert into users values (#{id}, #{name}, #{salary})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("select * from users")
    List<User> select();

    @Select("select * from users where id = #{id}")
    User selectById(Long id);

    @Update("update users set name=#{name}, salary=#{salary} where id = #{id}")
    void update(User user);

    @Delete("delete from users where id = #{id}")
    void delete(Long id);

    @Select("""
            select * from users where
                (name like #{user.name} or #{user.name} = '%null%') and
                (salary = #{user.salary} or #{user.salary} is null)
            order by ${sort} limit #{pageSize} offset #{pageNumber}
            """)
    List<User> filter(User user, Integer pageNumber, Integer pageSize, String sort);

}
