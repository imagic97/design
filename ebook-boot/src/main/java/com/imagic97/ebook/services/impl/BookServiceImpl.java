package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.BookInfoMapper;
import com.imagic97.ebook.dao.BookMapper;
import com.imagic97.ebook.entity.Book;
import com.imagic97.ebook.services.BookService;
import com.imagic97.ebook.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
@Service
public class BookServiceImpl implements BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource
    private BookInfoMapper bookInfoMapper;

    @Override
    public Book selectBookById(long bookId) {
        return bookMapper.selectBookById(bookId);
    }

    @Override
    public Integer addBook(Book book) {
        book.setUploadDate(StringUtil.getCurrentTimeStamp());
        return bookMapper.addBook(book);
    }

    @Override
    public Integer modifyBook(long bookId, Integer category, Integer isShare) {
        return bookMapper.modifyBook(bookId, category, isShare);
    }

    @Override
    public Integer modifyBook(Book book) {
        return bookMapper.modifyBookAll(book);
    }

    @Override
    public Integer deleteBookById(long bookId) {
        bookInfoMapper.deleteBookInfoByBookId(bookId);
        return bookMapper.deleteBookById(bookId);
    }

    @Override
    public List<Book> selectBookByUserId(long userId) {
        return bookMapper.selectBookByUserId(userId);
    }
}
