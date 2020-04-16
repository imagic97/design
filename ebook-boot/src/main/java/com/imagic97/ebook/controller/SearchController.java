package com.imagic97.ebook.controller;

import com.imagic97.ebook.annotation.PassToken;
import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.services.BookInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author imagic
 */
@RestController
public class SearchController {

    @Resource
    private BookInfoService bookInfoService;

    @PassToken
    @GetMapping("/search")
    @ApiOperation("搜索电子书")
    public ResultBody searchBook(@RequestParam String keyWords,@RequestParam(required = false,defaultValue = "0") int offset){
        if(keyWords.length()>25) return ResultBody.success(null);
        if(keyWords.length()==0) return ResultBody.success(null);
        return ResultBody.success(bookInfoService.searchBook(keyWords,offset));
    }
}
