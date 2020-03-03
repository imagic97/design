package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.UserMapper;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.services.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User userLogin(String userName, String userPassword) {
        return userMapper.userLogin(userName,userPassword);
    }

    @Override
    public Integer userAdd(User user) {
        return userMapper.userAdd(user);
    }

    @Override
    public List<User> selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }
}
