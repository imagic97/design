package com.imagic97.ebook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * book_info表实体映射
 *
 * @author imagic
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookInfo {
    private long bookInfoId;
    private long bookId;
    private String title;
    private String Introduction;
    private String language;
    private String publisher;
    private String creator;
    private String ISBN;
    private String publishDate;
}
