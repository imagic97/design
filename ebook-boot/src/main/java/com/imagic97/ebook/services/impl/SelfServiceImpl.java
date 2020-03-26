package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.SelfMapper;
import com.imagic97.ebook.dto.SelfBook;
import com.imagic97.ebook.entity.Self;
import com.imagic97.ebook.services.SelfService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
@Service
public class SelfServiceImpl implements SelfService {
    @Resource
    private SelfMapper selfMapper;

    @Override
    public Integer addBookToSelf(Self self) {
        return selfMapper.addBookToSelf(self);
    }

    @Override
    public Integer deleteSelf(long selfId, long userId) {
        return selfMapper.deleteSelf(selfId, userId);
    }

    @Override
    public List<SelfBook> selectSelfByUserId(long userId) {
        return selfMapper.selectSelfByUserId(userId);
    }
}
