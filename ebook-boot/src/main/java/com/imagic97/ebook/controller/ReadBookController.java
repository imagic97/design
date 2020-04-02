package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.epub.ContentItem;
import com.imagic97.ebook.epub.EpubMenuParser;
import com.imagic97.ebook.epub.Reader;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.util.ResponseContentType;
import io.swagger.annotations.ApiOperation;
import nl.siegmann.epublib.domain.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Controller
@RequestMapping("/read")
@ApiOperation("电子书阅读模块")
public class ReadBookController {

    @Value("${BOOK_PATH}")
    private String BOOK_PATH;

    /**
     * 获取书籍资源文件,如图片、html、css
     * 章节通过href获取，图片资源则为id
     *
     * @param file 书籍文件名
     * @param href 指定资源名
     */
    @GetMapping(value = "/view")
    @ApiOperation("获取电子书资源，如HTML、png等")
    public void view(@RequestParam String file,
                     @RequestParam String href, HttpServletResponse response) {
        String type = ResponseContentType.getInstance().matchType(href, ".");
        response.setContentType(type);
        byte[] data = new Reader(BOOK_PATH + file, href).getResourceData();
        if (data == null) {
            throw new MessageException("404", "资源不存在");
        } else {
            try {
                FileCopyUtils.copy(new ByteArrayInputStream(data), response.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.getOutputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 获取书籍封面,
     *
     * @param file 书籍文件名
     */
    @GetMapping(value = "/cover")
    @ResponseBody
    @ApiOperation("获取电子书封面")
    public void getCover(@RequestParam String file, HttpServletResponse response) {
        response.setContentType("image/png");
        Reader reader = new Reader(BOOK_PATH + file);
        byte[] data;

        try {
            Resource resource = reader.getBook().getCoverImage();
            if (resource == null) {
                return;
            }
            data = resource.getData();
            if (data != null) {
                FileCopyUtils.copy(new ByteArrayInputStream(data), response.getOutputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取书籍目录
     *
     * @param file 书籍文件名
     * @return String json格式目录字符串
     */
    @GetMapping(value = "/content")
    @ResponseBody
    @ApiOperation("获取电子书目录")
    public ResultBody getContentItem(@RequestParam String file) {
        Reader reader = new Reader(BOOK_PATH + file);
        ContentItem contentItem = new EpubMenuParser().startParse(reader.getBook());
        return ResultBody.success(contentItem);
    }

    @GetMapping(value = "/css")
    @ResponseBody
    @ApiOperation("获取电子书样式")
    public ResultBody getBookCSS(@RequestParam String file) {
        Reader reader = new Reader(BOOK_PATH + file);
        String cssStyle = reader.getCSS();
        if (cssStyle != null && !cssStyle.equals(""))
            return ResultBody.success(reader.getCSS());
        else return ResultBody.error("无样式");
    }

}
