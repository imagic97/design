package com.imagic97.ebook.controller;

import com.imagic97.ebook.annotation.PassToken;
import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.BookInfoService;
import com.imagic97.ebook.services.BookService;
import com.imagic97.ebook.services.TokenService;
import com.imagic97.ebook.services.UserService;
import com.imagic97.ebook.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author imagic
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private BookInfoService bookInfoService;
    @Resource
    private BookService bookService;

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @GetMapping("/getBookInfoList")
    @ApiOperation("获取书籍详情列表")
    public ResultBody getBookList(@RequestParam(defaultValue = "10") int num,
                                  @RequestParam(defaultValue = "0") int offset) {
        if (num < 0 || offset < 0) {
            return ResultBody.success(null);
        }
        return ResultBody.success(bookInfoService.selectBookInfoList(num, offset));
    }

    @PassToken
    @PostMapping("/adminLogin")
    @ApiOperation("管理员登录")
    public ResultBody login(@RequestParam String userName,
                            @RequestParam String password) {
        if ("".equals(userName) || "".equals(password))
            throw new MessageException("1", "请输入用户名和密码");
        User user = userService.userLogin(userName, password);
        if (user != null && user.getUserName() != null) {
            if (user.getType() == 1) {
                return ResultBody.error("该账户非管理员账户");
            }
            TokenUtil.getSession().setAttribute("user", user);
            return ResultBody.success(tokenService.getToken(user));
        }
        return ResultBody.error("用户名或密码错误");
    }

    @GetMapping("/getBookTotal")
    @ApiOperation("获取书籍详情列表")
    public ResultBody getBookTotal() {

        return ResultBody.success(bookService.selectBookTotal());
    }

}
