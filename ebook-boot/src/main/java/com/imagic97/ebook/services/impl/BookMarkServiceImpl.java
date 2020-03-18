package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.BookMarkMapper;
import com.imagic97.ebook.entity.BookMark;
import com.imagic97.ebook.services.BookMarkService;
import com.imagic97.ebook.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author imagic
 */
@Service
public class BookMarkServiceImpl implements BookMarkService {
    @Resource
    private BookMarkMapper bookMarkMapper;

    @Override
    public Integer addBookMark(BookMark bookMark) {
        bookMark.setCreateDate(StringUtil.getCurrentTimeStamp());
        return bookMarkMapper.addBookMark(bookMark);
    }

    @Override
    public BookMark selectBookMarkByBookId(long bookId) {
        return bookMarkMapper.selectBookMarkByBookId(bookId);
    }

    @Override
    public BookMark selectBookMarkByUserId(long userId) {
        return bookMarkMapper.selectBookMarkByUserId(userId);
    }

    @Override
    public Integer deleteBookMarkById(long bookMarkId) {
        return bookMarkMapper.deleteBookMarkById(bookMarkId);
    }
}
