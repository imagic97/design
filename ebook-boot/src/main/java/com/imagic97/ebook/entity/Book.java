package com.imagic97.ebook.entity;

import lombok.*;

/**
 * @author imagic
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long bookId;
    private String bookName;
    private String author;
    private String uploadDate;
}
