package com.imagic97.ebook.util;

import java.io.FileOutputStream;

public class Buff2Image {

//    buff2Image(b3, path+"\\test.jpg");

    public static void buff2Image(byte[] b,String tagSrc) throws Exception {
        FileOutputStream fout = new FileOutputStream(tagSrc);
        //将字节写入文件
        fout.write(b);
        fout.close();
    }
}
