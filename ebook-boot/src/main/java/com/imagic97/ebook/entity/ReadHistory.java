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
public class ReadHistory {
    private long readHistoryId;
    private long userId;
    private long bookId;
    private String chapter;
}
