package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.BookComment;

/**
 * @author imagic
 */
public interface BookCommentService {

    Integer addComment(BookComment bookComment);

    Integer deleteCommentById(long commentId);

    Integer modifyComment(String content);

    BookComment selectCommentByBookId(long bookId);

    BookComment selectCommentByUserId(long userId);

}
