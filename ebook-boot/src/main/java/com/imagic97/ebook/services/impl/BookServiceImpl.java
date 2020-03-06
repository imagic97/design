package com.imagic97.ebook.services.impl;

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
    public Integer modifyBook(Book book) {
        return bookMapper.modifyBook(book);
    }

    @Override
    public Integer deleteBookById(String bookId) {
        return bookMapper.deleteBookById(bookId);
    }

    @Override
    public List<Book> selectBookByBookName(String bookName) {
        return bookMapper.selectBookByBookName(bookName);
    }

    @Override
    public List<Book> selectBookByAuthor(String author) {
        return bookMapper.selectBookByAuthor(author);
    }

    @Override
    public List<Book> selectBookByUserId(String userId) {
        return bookMapper.selectBookByUserId(userId);
    }
}
