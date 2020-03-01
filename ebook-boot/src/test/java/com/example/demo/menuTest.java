package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.example.demo.epub.ContentItem;
import com.example.demo.epub.EpubMenuParser;
import com.example.demo.util.Buff2Image;
import net.sf.jazzlib.ZipFile;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.service.MediatypeService;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;


public class menuTest {
    public static void main(String[] args) {
        try {
            EpubReader epubReader = new EpubReader();
            MediaType[] lazyTypes = {

                    MediatypeService.CSS,

                    MediatypeService.GIF, MediatypeService.JPG,

                    MediatypeService.PNG,

                    MediatypeService.MP3,

                    MediatypeService.MP4};

            String path = "D:/imagic/lang/idea/dad/src/main/resources/static/book/3.epub";
            ZipFile zipFile = new ZipFile(path);
//
            Book book = epubReader.readEpubLazy(zipFile, "UTF-8", Arrays.asList(lazyTypes));

            ContentItem contentItem = new EpubMenuParser().startParse(book);
            Object o = JSON.toJSON(contentItem);
            String str = o.toString();
            System.out.println(str);


            //通过href获取
            //String  string = contentItem.getChildren().get(3).getUrl();

            //Resource byHref = book.getResources().getByHref("OEBPS/Text/Section0001_split_000.xhtml#epubw_com_id_2");


            //System.out.println(byHref.getSize() + "---------------------html--------------------------------");
            //String string = new String(byHref.getData());
            //System.out.println(string);


            //String str1 =string.substring(1500,8889);
            //System.out.println(str1);
            //System.out.println(string.indexOf("epubw_com_id_3"));
            //Buff2Image.buff2Image(byHref.getData(),"OEBPS/Text/Section0001_split_000.xhtml#epubw_com_id_2");


            List<ContentItem> child = contentItem.getChildren();
            child.forEach(contentItem1 -> {
                if (contentItem1.getChildren().isEmpty())
                    System.out.println("title: " + contentItem1.getTitle() + "    href:" + contentItem1.getUrl());
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
