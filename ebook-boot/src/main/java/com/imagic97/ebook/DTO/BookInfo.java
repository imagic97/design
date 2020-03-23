package com.imagic97.ebook.DTO;

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
public class BookInfo {
    private String title;
    private String Introduction;
    private String language;
    private String publisher;
    private String creator;
    private String ISBN;
    private String publishDate;
}
