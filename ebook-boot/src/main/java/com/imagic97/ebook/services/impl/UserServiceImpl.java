package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.UserMapper;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.services.UserService;
import com.imagic97.ebook.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User userLogin(String userName, String userPassword) {
        return userMapper.userLogin(userName, userPassword);
    }

    @Override
    public Integer userAdd(User user) {
        user.setCreateDate(StringUtil.getCurrentTimeStamp());
        return userMapper.userAdd(user);
    }

    @Override
    public List<User> selectUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    @Override
    public String getUserCount() {
        return userMapper.getUserCount();
    }

    @Override
    public List<User> selectAllUser(Integer limit, Integer offset) {
        return userMapper.selectAllUser(limit, offset);
    }

    @Override
    public Integer modifyUser(User user) {
        return userMapper.modifyUser(user);
    }

    @Override
    public Integer makeUserFrozenById(String userId) {
        return userMapper.makeUserFrozenById(userId);
    }

    @Override
    public Integer modifyUserStatus(User user) {
        return userMapper.modifyUserStatus(user);
    }

    @Override
    public User selectUserById(long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    public Integer deleteUserById(long userId) {
        return userMapper.deleteUserById(userId);
    }
}
