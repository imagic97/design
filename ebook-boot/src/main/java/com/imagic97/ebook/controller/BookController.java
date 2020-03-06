package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.services.UserService;
import com.imagic97.ebook.util.FileOperate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author imagic
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private UserService userService;

    @RequestMapping("/upload")
    public ResultBody bookUpload(@RequestParam MultipartFile multipartFile, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return ResultBody.error("用户未登录，不能上传文件");
        boolean isSuccess = new FileOperate().fileUpload(multipartFile,user.getUserId());
        if (isSuccess)return ResultBody.error("文件已存在");
        return ResultBody.success("文件上传成功");
    }
}
