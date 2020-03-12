package com.imagic97.ebook.entity;

import lombok.*;

/**
 * book表实体
 *
 * @author imagic
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long bookId;
    private long userId;
    private String fileName;
    private String bookName;
    private String author;
    private String introduction;
    private String publisher;
    private Integer bookCategory;
    private Integer isShare;
    private String uploadDate;
}



//<dc:language>zh</dc:language>
////<dc:publisher>人民邮电出版社</dc:publisher>
////<meta name="cover" content="cover"/>
////<dc:creator opf:file-as="未知" opf:role="aut">休斯-克劳奇 (Tom Hughes-Croticher)</dc:creator>
////<dc:creator opf:file-as="未知" opf:role="aut">ePUBw.COM</dc:creator>
////<dc:contributor opf:role="bkp">calibre (3.38.0) [http://calibre-ebook.com]</dc:contributor>
////<dc:identifier opf:scheme="ISBN">9787115306180</dc:identifier>
////<dc:identifier opf:scheme="MOBI-ASIN">B00JVLEZ32</dc:identifier>
////<dc:identifier id="uuid_id" opf:scheme="uuid">fbd0cdb1-2a04-4c55-a17d-56c6befeaae9</dc:identifier>
////<dc:date>2013-01-31T16:00:00+00:00</dc:date>
////<dc:title>Node即学即用 (图灵程序设计丛书 35)</dc:title>
////<meta name="calibre:timestamp" content="2019-04-13T12:21:30.240000+00:00"/>
////<dc:rights>Copyright © 2013 人民邮电出版社</dc:rights>
////<meta content="horizontal-lr" name="primary-writing-mode"/>