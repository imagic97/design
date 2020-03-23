package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.Book;

import java.util.List;

/**
 * @author imagic
 */
public interface BookService {

    Book selectBookById(long bookId);

    Integer addBook(Book book);

    Integer modifyBook(Book book);

    Integer modifyBook(long bookId, Integer calagory, Integer isShare);

    Integer deleteBookById(long bookId);

    List<Book> selectBookByUserId(long userId);

    List<Book> selectBookByCategoryId(int categoryId);
}
