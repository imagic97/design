package com.example.demo;

import com.example.demo.epub.ContentItem;
import com.example.demo.epub.EpubMenuParser;
import net.sf.jazzlib.ZipFile;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.service.MediatypeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.TOCReference;

@Controller
public class test {
    static List<TOCReference> tbList = new ArrayList<TOCReference>();

    @RequestMapping("/test")
    @ResponseBody
    public String  tets() {
        try {

            EpubReader epubReader = new EpubReader();



            MediaType[] lazyTypes = {

                    MediatypeService.CSS,

                    MediatypeService.GIF, MediatypeService.JPG,

                    MediatypeService.PNG,

                    MediatypeService.MP3,

                    MediatypeService.MP4};

            String path = ResourceUtils.getURL("classpath:").getPath() + "/static/book/3.epub";
            ZipFile zipFile = new ZipFile(path);
//
            Book book = epubReader.readEpubLazy(zipFile,"UTF-8", Arrays.asList(lazyTypes));

            ContentItem contentItem  = new ContentItem();

            contentItem = new EpubMenuParser().startParse(book);

            List<ContentItem> child = contentItem.getChildren();

            child.forEach(contentItem1 -> {
                if(contentItem1.getChildren() == null)
                    System.out.println("title: "+contentItem1.getTitle() + "href:"+contentItem1.getUrl());
                else {
                    System.out.println("sd");
                }
            });


//            int index = 3;
//            Resource byIndex = book.getSpine().getResource(index);
//            System.out.println(byIndex.toString());
//            System.out.println(byIndex.getHref());
//
//            TableOfContents tableOfContents = book.getTableOfContents();
//
//            System.out.println(tableOfContents.size());


//            // read epub file
//            EpubReader epubReader = new EpubReader();
//            Book book = epubReader.readEpub(new FileInputStream(path));
//
//            // print the first title
//            List<String> titles = book.getMetadata().getTitles();
//
//            System.out.println("book title:"+ titles.size() + (titles.isEmpty() ? "book has no title" : titles.get(0)));



        } catch (Exception e) {

            e.printStackTrace();

        }
        return "tets ";
    }


    public static void getNode(TOCReference toc, Integer level) {
        List<TOCReference> tocList = toc.getChildren();
        if (tocList != null && tocList.size() > 0) {// 判断是否查到,没有了表示小的了

            if (level == 2) {
                tbList.add(toc);
            }
            for (int i = 0; i < tocList.size(); i++) {
                getNode(tocList.get(i), level + 1);

            }
        }
    }
}
