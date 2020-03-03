package com.imagic97.ebook.daoTest;

import com.imagic97.ebook.dao.UserMapper;
import com.imagic97.ebook.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author imagic
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class userDaoTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void userLogin(){
        User user = userMapper.userLogin("root","123456");
        System.out.println(user.toString());
    }


}
