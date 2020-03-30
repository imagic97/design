package com.imagic97.ebook.services;

import com.imagic97.ebook.dto.SelfBook;
import com.imagic97.ebook.entity.Self;

import java.util.List;

/**
 * @author imagic
 */
public interface SelfService {

    Integer addBookToSelf(Self self);

    Integer deleteSelf(long selfId, long userId);

    Integer deleteSelfByBookId(long bookId, long userId);

    List<SelfBook> selectSelfByUserId(long userId);
}
