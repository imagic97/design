package com.imagic97.ebook.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * comment表实体
 *
 * @author imagic
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookComment {
    private long commentId;
    private long userId;
    private String content;
    private String updateDate;
    private String createDate;
}
