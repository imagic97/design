package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.BookMarkMapper;
import com.imagic97.ebook.entity.BookMark;
import com.imagic97.ebook.services.BookMarkService;
import com.imagic97.ebook.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<BookMark> selectBookMarkByBookId(long bookId,long userId) {
        return bookMarkMapper.selectBookMarkByBookId(bookId,userId);
    }

    @Override
    public List<BookMark> selectBookMarkByUserId(long userId) {
        return bookMarkMapper.selectBookMarkByUserId(userId);
    }

    @Override
    public Integer deleteBookMarkById(long bookMarkId) {
        return bookMarkMapper.deleteBookMarkById(bookMarkId);
    }
}
