package com.imagic97.ebook.controller;

import com.imagic97.ebook.entity.ResponseSwap;
import com.imagic97.ebook.entity.User;
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
    public ResponseSwap<String> login(@RequestParam String userName,
                                      @RequestParam String password,
                                      HttpSession httpSession) {
        if ("".equals(userName) || "".equals(password))
            return new ResponseSwap<>(201, "请输入用户名和密码");
        User user = userService.userLogin(userName, password);
        if (user.getUserName() != null) {
            httpSession.setAttribute("user", user);
            return new ResponseSwap<>(200, "登录成功");
        }
        return new ResponseSwap<>(202, "失败！请检查用户名或密码");
    }

    @RequestMapping("register")
    public ResponseSwap<String> addUser(@RequestParam String userName,
                                        @RequestParam String password,
                                        @RequestParam(required = false) String email) {
        if ("".equals(userName) || "".equals(password))
            return new ResponseSwap<>(201, "请输入用户名和密码");
        if(userService.selectUserByName(userName)!=null)return new ResponseSwap<>(204,"用户名已存在");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        User user = User.builder()
                .userName(userName)
                .userPassword(password)
                .email(email)
                .type(1)
                .state(0)
                .createDate(formatter.format(date)).build();
        return userService.userAdd(user) > 0 ? new ResponseSwap<>(200, "注册成功") : new ResponseSwap<>(203, "注册失败");
    }


}
