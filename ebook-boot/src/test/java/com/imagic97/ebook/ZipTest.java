package com.imagic97.ebook;

import com.imagic97.ebook.util.ResponseContentType;

public class ZipTest {
    public static void main(String[] args) {

        System.out.println(ResponseContentType.getInstance().matchType("dou.jpg","."));
    }
}
