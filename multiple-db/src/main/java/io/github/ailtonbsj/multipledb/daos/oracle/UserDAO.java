package io.github.ailtonbsj.multipledb.daos.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import io.github.ailtonbsj.multipledb.models.oracle.User;

@Mapper
public interface UserDAO {

    @Select("SELECT * FROM MULTIDB.USERS")
    @Results(id = "userResultMap", value = {
            @Result(property = "isActive", column = "IS_ACTIVE"),
            @Result(property = "createdAt", column = "CREATED_AT"),
            @Result(property = "departmentId", column = "DEPARTMENT_ID"),
            @Result(property = "profileId", column = "PROFILE_ID")
    })
    List<User> findAll();

}
