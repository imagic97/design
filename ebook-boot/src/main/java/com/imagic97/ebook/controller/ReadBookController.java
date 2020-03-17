package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.epub.ContentItem;
import com.imagic97.ebook.epub.EpubMenuParser;
import com.imagic97.ebook.epub.Reader;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.util.ResponseContentType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/read")
public class ReadBookController {
    /**
     * 获取书籍资源文件,如图片、html、css
     * 章节通过href获取，图片资源则为id
     *
     * @param file     书籍文件名
     * @param href     指定资源名
     * @param response 响应请求
     */
    @RequestMapping(value = "/view")
    public void view(@RequestParam String file,
                     @RequestParam String href,
                     HttpServletResponse response) {
        String type = ResponseContentType.getInstance().matchType(href, ".");
        response.setContentType(file);
        byte[] data = new Reader(file, href).getResourceData();
        if (data == null) {

            throw new MessageException("5", "资源不存在");
        } else {
            try {
                FileCopyUtils.copy(data, response.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取书籍封面,
     *
     * @param file     书籍文件名
     * @param response 响应请求
     */
    @RequestMapping(value = "/cover")
    @ResponseBody
    public void getCover(@RequestParam String file,
                         HttpServletResponse response) {
        response.setContentType("image/png");
        Reader reader = new Reader(file);
        InputStream fileInputStream;
        byte[] data=null;
        try {
            data =reader.getBook().getCoverImage().getData();
            if(data == null) fileInputStream = new FileInputStream("");

                FileCopyUtils.copy(new ByteArrayInputStream(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取书籍目录
     *
     * @param file 书籍文件名
     * @return String json格式目录字符串
     */
    @RequestMapping(value = "/content")
    @ResponseBody
    public ResultBody getContentItem(@RequestParam String file) {
        Reader reader = new Reader(file);
        ContentItem contentItem = new EpubMenuParser().startParse(reader.getBook());
        return ResultBody.success(contentItem);
    }

    @RequestMapping(value = "/css")
    @ResponseBody
    public ResultBody getBookCSS(@RequestParam String file) {
        Reader reader = new Reader(file);
        String cssStyle = reader.getCSS();
        if (cssStyle != null && !cssStyle.equals(""))
            return ResultBody.success(reader.getCSS());
        else return ResultBody.error("样式空");
    }

}
