package com.example.demo;

import com.example.demo.util.ResponseContentType;

public class ZipTest {
    public static void main(String[] args) {

        System.out.println(ResponseContentType.getInstance().matchType("dou.jpg","."));
    }
}
