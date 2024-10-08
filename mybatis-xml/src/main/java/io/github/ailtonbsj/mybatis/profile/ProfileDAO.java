package io.github.ailtonbsj.mybatis.profile;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ProfileDAO {

    // @Select("select * from profiles")
    List<Profile> findAll();

    // @Select("select * from profiles where id = #{id}")
    Optional<Profile> findById(Long id);

    // @Delete("delete from profiles where id = #{id}")
    void deleteById(Long id);

    // @Insert("""
    //         insert into profiles (
    //             avatar_url, biography, birthday, country, instagram, name, phone, salary
    //         ) values (
    //             #{avatarUrl}, #{biography}, #{birthday}, #{country}, #{instagram}, #{name}, #{phone}, #{salary}
    //         )
    //         """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(Profile model);

    // @Update("""
    //         update profiles set
    //             avatar_url = #{avatarUrl}, biography = #{biography}, birthday = #{birthday}, country = #{country},
    //             instagram = #{instagram}, name = #{name}, phone = #{phone}, salary = #{salary}
    //         where id = #{id}
    //         """)
    void update(Profile model);

    default Profile save(Profile model) {
        if (findById(model.getId()).isPresent())
            update(model);
        else
            insert(model);
        return findById(model.getId()).get();
    }
    
}
