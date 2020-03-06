package com.imagic97.ebook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author imagic
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMark {
    private long bookMarkId;
    private long userId;
    private long bookId;
    private String chapters;
    private String createDate;
}
