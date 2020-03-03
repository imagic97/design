package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author imagic
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    @RequestMapping("/login")
    public ResultBody login(@RequestParam String userName,
                            @RequestParam String password,
                            HttpSession httpSession) {
        if ("".equals(userName) || "".equals(password))
            throw new MessageException("1", "请输入用户名和密码");
        User user = userService.userLogin(userName, password);
        if (user != null && user.getUserName() != null) {
            httpSession.setAttribute("user", user);
            return ResultBody.success(null);
        }
        return ResultBody.error("用户名或密码错误");
    }

    @RequestMapping("register")
    public ResultBody addUser(@RequestParam String userName,
                                        @RequestParam String password,
                                        @RequestParam(required = false) String email) {
        if ("".equals(userName) || "".equals(password))
            return ResultBody.error("请输入用户名和密码");
        if (userService.selectUserByName(userName).size()>0) return ResultBody.error("用户名已被注册");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        User user = User.builder()
                .userName(userName)
                .userPassword(password)
                .email(email)
                .type(1)
                .state(0)
                .createDate(formatter.format(date)).build();
        return userService.userAdd(user) > 0 ? ResultBody.success("注册成功") : ResultBody.error("注册失败，未知错误");
    }


}
