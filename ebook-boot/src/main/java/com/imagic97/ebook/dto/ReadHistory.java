package com.imagic97.ebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 阅读记录
 * @author imagic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadHistory {
    private String fileName;
    private String chapter;
}
