package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
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
        if (httpSession.getAttribute("user") == null)
            return ResultBody.error("用户未登录，不能上传文件");
        String fileName = new FileOperate().fileUpload(multipartFile);
        if (fileName != null){
            ;
        }
        return ResultBody.success("文件上传成功");
    }
}
