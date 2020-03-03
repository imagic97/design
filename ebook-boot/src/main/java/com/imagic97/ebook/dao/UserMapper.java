package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author imagic
 */
@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE user_name=#{userName} AND user_password=#{password}")
    public User userLogin(@Param("userName") String userName,@Param("password") String password);

    @Insert("INSERT INTO user(user_name,user_password,email,type,state,create_date)" +
            " VALUE(#{userName},#{userPassword},#{email},#{type},#{state},#{createDate})")
    public  Integer userAdd(User user);

    @Select("select * from user where user_name=#{name}")
    public List<User> selectUserByName(String name);
}
