package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.dto.SelfBook;
import com.imagic97.ebook.entity.Book;
import com.imagic97.ebook.entity.Self;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.BookService;
import com.imagic97.ebook.services.SelfService;
import com.imagic97.ebook.services.UserService;
import com.imagic97.ebook.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author imagic
 */

@RestController
@RequestMapping("/user")
@ApiOperation("用户模块")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private SelfService selfService;

    @Resource
    private BookService bookService;

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
        return userService.modifyUser(user) > 0 ? ResultBody.success("修改成功") : ResultBody.error("修改失败");
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

    @GetMapping("/getUserSelf")
    @ApiOperation("获取用户书架")
    public ResultBody getUserSelf(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        List<Book> bookList = bookService.selectBookByUserId(user.getUserId());
        List<SelfBook> selfList = selfService.selectSelfByUserId(user.getUserId());
        return ResultBody.success(selfList);
    }

    @GetMapping("/addBookToSelf")
    @ApiOperation("用户添加书到书架")
    public ResultBody addBookToSelf(@RequestParam long bookId,
                                    HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Book book = bookService.selectBookById(bookId);
        if (book == null) return ResultBody.error("暂无此书");
        if (book.getIsShare() < 1) return ResultBody.error("此书不可共享");

        Self self = Self.builder()
                .bookId(bookId)
                .userId(user.getUserId())
                .createDate(StringUtil.getCurrentTimeStamp()).build();

        if (selfService.addBookToSelf(self) > 0)
            return ResultBody.success(null);
        return ResultBody.error("添加失败");
    }

    @GetMapping("/deleteBookFromSelf")
    @ApiOperation("用户从书架删除书")
    public ResultBody deleteBookFromSelf(@RequestParam long selfId,
                                         HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (selfService.deleteSelf(selfId, user.getUserId()) > 0) {
            return ResultBody.success(null);
        }
        return ResultBody.error("删除失败");
    }

}
