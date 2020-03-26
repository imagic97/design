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
@AllArgsConstructor
@NoArgsConstructor
public class Self {
    private long selfId;
    private long userId;
    private long bookId;
    private String createDate;
}
