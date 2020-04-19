package com.imagic97.ebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author imagic
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInfoDTO {
    private String bookId;
    private String userId;
    private String title;
    private String Introduction;
    private String language;
    private String publisher;
    private String creator;
    private String ISBN;
    private String publishDate;
    private String fileName;
    private String isShare;
    private String bookCategory;

}
