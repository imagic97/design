package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.ReadHistoryMapper;
import com.imagic97.ebook.entity.ReadHistory;
import com.imagic97.ebook.services.ReadHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
@Service
public class ReadHistoryServiceImpl implements ReadHistoryService {

    @Resource
    ReadHistoryMapper readHistoryMapper;

    @Override
    public ReadHistory selectReadHistoryByBookId(long bookId) {
        return readHistoryMapper.selectReadHistoryByBookId(bookId);
    }

    @Override
    public Integer addReadHistory(ReadHistory readHistory) {
        return readHistoryMapper.addReadHistory(readHistory);
    }

    @Override
    public List<ReadHistory> selectReadHistoryByUserId(long userId) {
        return readHistoryMapper.selectReadHistoryByUserId(userId);
    }

    @Override
    public Integer deleteReadHistoryById(long bookId) {
        return readHistoryMapper.deleteReadHistoryById(bookId);
    }

    @Override
    public Integer modifyReadHistory(ReadHistory readHistory) {
        return readHistoryMapper.modifyReadHistory(readHistory);
    }
}
