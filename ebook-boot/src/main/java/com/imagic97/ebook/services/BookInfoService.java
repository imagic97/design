package com.imagic97.ebook.services;

import com.imagic97.ebook.dto.BookInfoDTO;
import com.imagic97.ebook.entity.BookInfo;

import java.util.List;

/**
 * @author imagic
 */
public interface BookInfoService {
    BookInfo selectBookInfoById(long bookId);

    Integer addBookInfo(BookInfo bookInfo);

    Integer modifyBookInfo(BookInfo bookInfo);

    Integer deleteBookInfoById(long bookId);

    List<BookInfoDTO> searchBook(String keyWords, int offset);
}
