package com.imagic97.ebook.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类下的电子书
 * @author imagic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryList {
    private long bookId;
    private String fileName;
    private String bookName;
}
