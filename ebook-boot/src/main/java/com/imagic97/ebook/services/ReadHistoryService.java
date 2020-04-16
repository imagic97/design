package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.ReadHistory;

import java.util.List;

/**
 * @author imagic
 */
public interface ReadHistoryService {
    Integer addReadHistory(ReadHistory readHistory);

    List<ReadHistory> selectReadHistoryByUserId(long userId);

    ReadHistory selectReadHistoryByBookId(long bookId);

    Integer deleteReadHistoryById(long bookId);

    Integer modifyReadHistory(ReadHistory readHistory);
}
