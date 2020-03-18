package com.imagic97.ebook.entity;

import lombok.*;

/**
 * book表实体
 *
 * @author imagic
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long bookId;
    private long userId;
    private String fileName;
    private Integer bookCategory;
    private Integer isShare;
    private String uploadDate;

}