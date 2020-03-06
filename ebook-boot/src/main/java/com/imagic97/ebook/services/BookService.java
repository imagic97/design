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

    Integer deleteBookById(String bookId);

    List<Book> selectBookByBookName(String bookName);

    List<Book> selectBookByAuthor(String author);

    List<Book> selectBookByUserId(String userId);
}
