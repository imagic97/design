package com.imagic97.ebook.controller;

import com.imagic97.ebook.annotation.PassToken;
import com.imagic97.ebook.annotation.UserLoginToken;
import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.dto.SelfBook;
import com.imagic97.ebook.entity.*;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.*;
import com.imagic97.ebook.util.StringUtil;
import com.imagic97.ebook.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    private BookMarkService bookMarkService;

    @Resource
    private ReadHistoryService readHistoryService;

    @Resource
    private SelfService selfService;

    @Resource
    private BookService bookService;

    @Resource
    private TokenService tokenService;

    @PassToken
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public ResultBody login(@RequestParam String userName,
                            @RequestParam String password) {
        if ("".equals(userName) || "".equals(password))
            throw new MessageException("1", "请输入用户名和密码");
        User user = userService.userLogin(userName, password);
        if (user != null && user.getUserName() != null) {
            if (user.getState() == 1) {
                return ResultBody.error("该账户被冻结");
            }
            if (!(user.getType() == 1)) {
                return ResultBody.error("该账户禁止登录");
            }
            TokenUtil.getSession().setAttribute("user", user);
            return ResultBody.success(tokenService.getToken(user));
        }
        return ResultBody.error("用户名或密码错误");
    }

    @GetMapping("/logout")
    @ApiOperation("退出登录")
    public ResultBody login() {
        TokenUtil.getSession().removeAttribute("user");
        return ResultBody.success(null);
    }

    @PassToken
    @GetMapping("/test")
    @ApiOperation("测试")
    public ResultBody test() {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        return ResultBody.success(user);
    }


    @PassToken
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
    public ResultBody deleteUser(@RequestParam long userId) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        if (user.getType() == 1) return ResultBody.error("非管理员，该操作无权限");
        if (userService.deleteUserById(userId) > 0) {
            return ResultBody.success("删除成功");
        }
        return ResultBody.error("删除失败");
    }

    @PostMapping("/modifyUser")
    @ApiOperation("修改用户信息")
    public ResultBody modifyUser(@RequestParam String userName,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam(required = false) String email) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        if (!user.getUserPassword().equals(oldPassword)) {
            return ResultBody.error("密码错误，拒绝修改操作");
        }
        user.setUserName(userName);
        user.setUserPassword(newPassword);
        user.setEmail(email);
        return userService.modifyUser(user) > 0 ? ResultBody.success("修改成功") : ResultBody.error("修改失败");
    }

    @PostMapping("/modifyUserStatus")
    @ApiOperation("修改用户状态")
    public ResultBody modifyUserStatus(@RequestParam long userId,
                                       @RequestParam Integer type,
                                       @RequestParam Integer state) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        if (user.getType() == 1) return ResultBody.error("无权限");
        User changeUser = userService.selectUserById(userId);
        if (changeUser == null) return ResultBody.error("查无此用户");
        changeUser.setState(state);
        changeUser.setType(type);
        return userService.modifyUserStatus(changeUser) > 0 ? ResultBody.success("修改成功") : ResultBody.error("修改失败");
    }

    @UserLoginToken
    @GetMapping("/getUserSelf")
    @ApiOperation("获取用户书架")
    public ResultBody getUserSelf() {
        // User user = (User) httpSession.getAttribute("user");

        long userId = TokenUtil.getTokenUserId();
        //用户自己上传书
        List<SelfBook> bookList = selfService.selectBookByUserId(userId);
        List<SelfBook> selfList = selfService.selectSelfByUserId(userId);
        selfList.addAll(bookList);
        return ResultBody.success(selfList);
    }

    @GetMapping("/addBookToSelf")
    @ApiOperation("用户添加书到书架")
    public ResultBody addBookToSelf(@RequestParam long bookId) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
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
    public ResultBody deleteBookFromSelf(@RequestParam long selfId) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        if (selfService.deleteSelf(selfId, user.getUserId()) > 0) {
            return ResultBody.success(null);
        }
        return ResultBody.error("删除失败");
    }

    @PostMapping("/addBookMark")
    @ApiOperation("用户添加书签")
    public ResultBody addBookMark(@RequestParam long bookId,
                                  @RequestParam String chapter) {
        User user = (User) TokenUtil.getSession().getAttribute("user");

        BookMark bookMark = BookMark.builder()
                .bookId(bookId)
                .userId(user.getUserId())
                .chapters(chapter)
                .createDate(StringUtil.getCurrentTimeStamp()).build();

        if (bookMarkService.addBookMark(bookMark) > 0) {
            return ResultBody.success("添加成功");
        }
        return ResultBody.error("添加失败");
    }

    @GetMapping("/getBookMark")
    @ApiOperation("用户获取指定书的书签")
    public ResultBody getBookMark(@RequestParam long bookId) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        List<BookMark> bookMarkList = bookMarkService.selectBookMarkByBookId(bookId, user.getUserId());
        return ResultBody.success(bookMarkList);
    }

    @GetMapping("/deleteBookMark")
    @ApiOperation("用户删除指定书签")
    public ResultBody deleteBookMark(@RequestParam long bookMarkId) {
        int result = bookMarkService.deleteBookMarkById(bookMarkId);
        if (result > 0) {
            return ResultBody.success("删除成功");
        }
        return ResultBody.success("删除失败");
    }

    @PostMapping("/addReadHistory")
    @ApiOperation("添加阅读记录")
    public ResultBody addReadHistory(@RequestParam long bookId,
                                     @RequestParam String chapter) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        ReadHistory readHistory = ReadHistory.builder()
                .bookId(bookId)
                .chapter(chapter)
                .userId(user.getUserId()).build();

        if (readHistoryService.modifyReadHistory(readHistory) > 0) {
            return ResultBody.success("添加成功");
        }
        if (readHistoryService.addReadHistory(readHistory) > 0) {
            return ResultBody.success("添加成功");
        }
        return ResultBody.error("添加失败");
    }

    @GetMapping("/getReadHistory")
    @ApiOperation("用户获取阅读记录")
    public ResultBody getReadHistory() {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        List<ReadHistory> bookMarkList = readHistoryService.selectReadHistoryByUserId(user.getUserId());
        return ResultBody.success(bookMarkList);
    }

}
