package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.BookCommentMapper;
import com.imagic97.ebook.entity.BookComment;
import com.imagic97.ebook.services.BookCommentService;
import com.imagic97.ebook.util.StringUtil;

import javax.annotation.Resource;

/**
 * @author imagic
 */
public class BookCommentServiceImpl implements BookCommentService {

    @Resource
    private BookCommentMapper bookCommentMapper;

    @Override
    public Integer addComment(BookComment bookComment) {
        bookComment.setCreateDate(StringUtil.getCurrentTimeStamp());
        return bookCommentMapper.addComment(bookComment);
    }

    @Override
    public Integer deleteCommentById(long commentId) {
        return bookCommentMapper.deleteCommentById(commentId);
    }

    @Override
    public Integer modifyComment(String content) {
        return bookCommentMapper.modifyComment(content);
    }

    @Override
    public BookComment selectCommentByBookId(long bookId) {
        return bookCommentMapper.selectCommentByBookId(bookId);
    }

    @Override
    public BookComment selectCommentByUserId(long userId) {
        return bookCommentMapper.selectCommentByUserId(userId);
    }
}
