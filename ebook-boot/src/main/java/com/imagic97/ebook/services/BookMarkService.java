package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.BookMark;

import java.util.List;

/**
 * @author imagic
 */
public interface BookMarkService {

    Integer addBookMark(BookMark bookMark);

    List<BookMark> selectBookMarkByBookId(long bookId,long userId);

    List<BookMark> selectBookMarkByUserId(long userId);

    Integer deleteBookMarkById(long bookMarkId);
}
