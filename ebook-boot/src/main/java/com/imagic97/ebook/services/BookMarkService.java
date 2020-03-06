package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.BookMark;

/**
 * @author imagic
 */
public interface BookMarkService {

    Integer addBookMark(BookMark bookMark);

    BookMark selectBookMarkByBookId(long bookId);

    BookMark selectBookMarkByUserId(long userId);

    Integer deleteBookMarkById(long bookMarkId);
}
