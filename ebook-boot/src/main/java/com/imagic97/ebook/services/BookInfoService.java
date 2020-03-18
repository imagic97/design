package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.BookInfo;

/**
 * @author imagic
 */
public interface BookInfoService {
    BookInfo selectBookInfoById(long bookId);

    Integer addBookInfo(BookInfo bookInfo);

    Integer modifyBook(BookInfo bookInfo);

    Integer deleteBookInfoById(long bookId);
}
