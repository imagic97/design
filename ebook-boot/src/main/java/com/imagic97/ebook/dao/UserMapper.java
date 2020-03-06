package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户表操作mapper
 * @author imagic
 */
@Mapper
public interface UserMapper {


    //登录
    @Select("SELECT * FROM user WHERE user_name=#{userName} AND user_password=#{password}")
    public User userLogin(@Param("userName") String userName, @Param("password") String password);

    //添加用户
    @Insert("insert into user(user_name,user_password,email,type,state,create_date)" +
            " VALUE(#{userName},#{userPassword},#{email},#{type},#{state},#{createDate})")
    public Integer userAdd(User user);

    //根据用户名查找用户
    @Select("select * from user where user_name=#{name}")
    public List<User> selectUserByName(String name);

    //返回user表长度
    @Select("select count(*) from user")
    public String getUserCount();

    //返回所有用户，返回条数limit，偏移量offset
    @Select("select * from user limit #{limit} offset #{offset}")
    public List<User> selectAllUser(@Param("limit") Integer limit, @Param("offset") Integer offset);

    //修改用户名，用户密码，邮箱
    @Update("update user set user_name = #{userName},user_password = #{userPassword},email=#{email} WHERE user_id= #{userId}")
    public Integer modifyUser(User user);

    //根据用户id冻结账户 0为正常，1为冻结
    @Update("update user set state = 1 where user_id = #{userId}")
    public Integer makeUserFrozenById(String userId);

    //根据id删除用户，不推荐
    @Delete("delete from user where user_id=#{userId} ")
    public Integer deleteUserById();
}
