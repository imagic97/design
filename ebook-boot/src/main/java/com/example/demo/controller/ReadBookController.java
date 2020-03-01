package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.epub.ContentItem;
import com.example.demo.epub.EpubMenuParser;
import com.example.demo.epub.Reader;
import com.example.demo.util.ResponseContentType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;


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
        response.setContentType(type);
        String path = getClass().getResource("/").getPath() + "static/book/" + file;

        byte[] data = new Reader(path, href).getResourceData();
        if (data == null) {
            System.out.println("error:404");
            response.setStatus(404);
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
        String path = getClass().getResource("/").getPath() + "static/book/" + file;
        Reader reader = new Reader(path);
        try {
            FileCopyUtils.copy(new ByteArrayInputStream(reader.getBook().getCoverImage().getData()), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取书籍目录
     *
     * @param file     书籍文件名
     * @param response 响应请求
     * @return String json格式目录字符串
     */
    @RequestMapping(value = "/content")
    @ResponseBody
    public String getContentItem(@RequestParam String file,
                                 HttpServletResponse response) {
        String path = getClass().getResource("/").getPath() + "static/book/" + file;
        Reader reader = new Reader(path);
        ContentItem contentItem = new EpubMenuParser().startParse(reader.getBook());
        return JSON.toJSONString(contentItem);
    }

}
