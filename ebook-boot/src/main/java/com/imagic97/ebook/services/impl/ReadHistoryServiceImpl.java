package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.ReadHistoryMapper;
import com.imagic97.ebook.entity.ReadHistory;
import com.imagic97.ebook.services.ReadHistoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
public class ReadHistoryServiceImpl implements ReadHistoryService {

    @Resource
    ReadHistoryMapper readHistoryMapper;

    @Override
    public Integer addReadHistory(ReadHistory readHistory) {
        return readHistoryMapper.addReadHistory(readHistory);
    }

    @Override
    public List<ReadHistory> selectReadHistoryByUserId(String userId) {
        return readHistoryMapper.selectReadHistoryByUserId(userId);
    }

    @Override
    public Integer deleteReadHistoryById(String bookId) {
        return readHistoryMapper.deleteReadHistoryById(bookId);
    }

    @Override
    public Integer modifyReadHistory(ReadHistory readHistory) {
        return readHistoryMapper.modifyReadHistory(readHistory);
    }
}
