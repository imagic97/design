package com.imagic97.ebook.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author imagic
 */
public class StringUtil {

    /**
     * 产生随机数字书名
     *
     * @return 字符串
     */
    public static String getBookName() {
        return String.valueOf(System.currentTimeMillis()) + new Random().nextInt(100);
    }

    /**
     * 产生秒级时间戳字符串
     *
     * @return 字符串
     */
    public static String getCurrentTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static String getCurrentTimeStampWithLong(long timeStamp) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(timeStamp);
        return formatter.format(date);
    }
}
