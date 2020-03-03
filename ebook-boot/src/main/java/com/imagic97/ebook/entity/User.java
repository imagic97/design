package com.imagic97.ebook.entity;

import lombok.*;

/**
 * @author imagic
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private long userId;
    private String userName;
    private String userPassword;
    private String email;
    private Integer type;
    private Integer state;
    private String updateDate;
    private String createDate;
}
