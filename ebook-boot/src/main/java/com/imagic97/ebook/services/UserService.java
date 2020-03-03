package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.User;

import java.util.List;

/**
 * @author imagic
 */
public interface UserService {
    User userLogin(String userName, String userPassword);
    Integer userAdd(User user);
    List<User> selectUserByName(String name);
}
