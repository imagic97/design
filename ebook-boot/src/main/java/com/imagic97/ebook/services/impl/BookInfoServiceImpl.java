package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.BookInfoMapper;
import com.imagic97.ebook.entity.BookInfo;
import com.imagic97.ebook.services.BookInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author imagic
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Resource
    private BookInfoMapper bookInfoMapper;

    @Override
    public BookInfo selectBookInfoById(long bookId) {
        return bookInfoMapper.selectBookInfoById(bookId);
    }

    @Override
    public Integer addBookInfo(BookInfo bookInfo) {
        return bookInfoMapper.addBookInfo(bookInfo);
    }

    @Override
    public Integer modifyBook(BookInfo bookInfo) {
        return bookInfoMapper.modifyBookInfo(bookInfo);
    }

    @Override
    public Integer deleteBookInfoById(long bookId) {
        return bookInfoMapper.deleteBookInfoById(bookId);
    }
}
