package com.imagic97.ebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 用户书架
 * @author imagic
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSelf {
    private long userId;
    private long bookId;
    private String fileName;
    private String bookName;
}
