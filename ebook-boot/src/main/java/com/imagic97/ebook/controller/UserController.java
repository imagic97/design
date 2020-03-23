package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author imagic
 */

@RestController
@RequestMapping("/user")
@ApiOperation("用户模块")
public class UserController {
    @Resource
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultBody login(@RequestParam String userName,
                            @RequestParam String password,
                            HttpSession httpSession) {
        if ("".equals(userName) || "".equals(password))
            throw new MessageException("1", "请输入用户名和密码");
        User user = userService.userLogin(userName, password);
        if (user != null && user.getUserName() != null) {
            if (user.getState() == 1) {
                return ResultBody.error("该账户被冻结");
            }
            httpSession.setAttribute("user", user);
            return ResultBody.success(null);
        }
        return ResultBody.error("用户名或密码错误");
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public ResultBody addUser(@RequestParam String userName,
                              @RequestParam String password,
                              @RequestParam(required = false) String email) {
        if ("".equals(userName) || "".equals(password))
            return ResultBody.error("请输入用户名和密码");
        if (userService.selectUserByName(userName).size() > 0) return ResultBody.error("用户名已被注册");
        User user = User.builder()
                .userName(userName)
                .userPassword(password)
                .email(email)
                .type(1)
                .state(0).build();
        return userService.userAdd(user) > 0 ? ResultBody.success("注册成功") : ResultBody.error("注册失败，未知错误");
    }

    @GetMapping("/deleteUser")
    @ApiOperation("管理员删除用户")
    public ResultBody deleteUser(@RequestParam long userId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user.getType() == 1) return ResultBody.error("用户无权限");
        if (userService.deleteUserById(userId) > 0) {
            return ResultBody.success("删除成功");
        }
        return ResultBody.error("删除失败");
    }

    @PostMapping("/modifyUser")
    @ApiOperation("修改用户信息")
    public ResultBody modifyUser(@RequestParam String userName,
                                 @RequestParam String password,
                                 @RequestParam(required = false) String email,
                                 HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if ("".equals(password)) {
            return ResultBody.error("请输入密码");
        }
        user.setUserName(userName);
        user.setUserPassword(password);
        user.setEmail(email);
        return userService.userAdd(user) > 0 ? ResultBody.success("修改成功") : ResultBody.error("修改失败");
    }

    @PostMapping("/modifyUserStatus")
    @ApiOperation("修改用户状态")
    public ResultBody modifyUserStatus(@RequestParam long userId,
                                       @RequestParam Integer type,
                                       @RequestParam Integer state,
                                       HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user.getType() == 1) return ResultBody.error("无权限");
        User changeUser = userService.selectUserById(userId);
        if (changeUser == null) return ResultBody.error("查无此用户");
        changeUser.setState(state);
        changeUser.setType(type);
        return userService.modifyUserStatus(changeUser) > 0 ? ResultBody.success("修改成功") : ResultBody.error("修改失败");
    }


}
