package io.github.ailtonbsj.mybatis.daos;

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

import io.github.ailtonbsj.mybatis.models.ActiveSession;

@Mapper
public interface ActiveSessionDAO {

    @Select("select * from active_session where id = #{id}")
    @Results(id = "activeSessionResultMap", value = {
            @Result(property = "signedIn", column = "signed_in"),
            @Result(property = "user", column = "user_id",
                many = @Many(select = "io.github.ailtonbsj.mybatis.daos.UserDAO.findById", fetchType = FetchType.LAZY))
    })
    Optional<ActiveSession> findById(Long id);

    @Select("select * from active_session")
    @ResultMap("activeSessionResultMap")
    List<ActiveSession> findAll();

    @Delete("delete from active_session where id = #{id}")
    void deleteById(Long id);

    @Insert("""
            insert into active_session (
                id, agent, device, signed_in, user_id
            ) values (
                #{id}, #{agent}, #{device}, #{signedIn}, #{user.id}
            )
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(ActiveSession model);

    @Update("""
            update active_session set
                agent = #{agent}, device = #{device}, signed_in = #{signedIn}, user_id = #{user.id}
            where id = #{id}
            """)
    void update(ActiveSession model);

    
    default ActiveSession save(ActiveSession model) {
        if (findById(model.getId()).isPresent()){
            update(model);
        } else {
            insert(model);
        }
        return findById(model.getId()).get();
    }
}
