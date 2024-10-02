package io.github.ailtonbsj.mybatis.activesession;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActiveSessionDAO {

    // final String USER_BY_ID = "io.github.ailtonbsj.mybatis.user.UserDAO.findById";

    // @Select("select * from active_session where id = #{id}")
    // @Results(id = "activeSessionResultMap", value = {
    //         @Result(property = "signedIn", column = "signed_in"),
    //         @Result(property = "user", column = "user_id", many = @Many(select = USER_BY_ID, fetchType = FetchType.LAZY))
    // })
    Optional<ActiveSession> findById(Long id);

    // @Select("select * from active_session")
    // @ResultMap("activeSessionResultMap")
    List<ActiveSession> findAll();

    // @Delete("delete from active_session where id = #{id}")
    void deleteById(Long id);

    // @Insert("""
    //         insert into active_session (
    //             agent, device, signed_in, user_id
    //         ) values (
    //             #{agent}, #{device}, #{signedIn}, #{user.id}
    //         )
    //         """)
    // @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(ActiveSession model);

    // @Update("""
    //         update active_session set
    //             agent = #{agent}, device = #{device}, signed_in = #{signedIn}, user_id = #{user.id}
    //         where id = #{id}
    //         """)
    void update(ActiveSession model);

    default ActiveSession save(ActiveSession model) {
        if (findById(model.getId()).isPresent()) {
            update(model);
        } else {
            insert(model);
        }
        return findById(model.getId()).get();
    }

    // @Select("select * from active_session where user_id = #{userId}")
    // @ResultMap("activeSessionResultMap")
    List<ActiveSession> findByUserId(Long userId);
}
