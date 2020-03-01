package com.example.demo.epub;

import com.example.demo.util.StringUtil;
import net.sf.jazzlib.ZipFile;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.service.MediatypeService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Reader {
    //epub文件
    private String bookFile;
    //指定书籍路径
    private String href;
    //单个书籍文件资源
    private Resource resource;
    //全部书籍文件资源
    private List<Resource> resourceList;
    //书籍缓存
    private Book book;

    public String getBookFile() {
        return bookFile;
    }

    public void setBookFile(String bookFile) {
        this.bookFile = bookFile;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader(String bookFile) {
        this.bookFile = bookFile;
        try {
            EpubReader epubReader = new EpubReader();
            nl.siegmann.epublib.domain.MediaType[] lazyTypes = {
                    MediatypeService.XHTML,
                    MediatypeService.CSS,
                    MediatypeService.GIF,
                    MediatypeService.JPG,
                    MediatypeService.PNG,
                    MediatypeService.MP3,
                    MediatypeService.MP4};
            ZipFile zipFile = new ZipFile(bookFile);
            this.book = epubReader.readEpubLazy(zipFile, "UTF-8", Arrays.asList(lazyTypes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Reader(String bookFile, String href) {
        this(bookFile);
        this.bookFile = bookFile;
        this.href = href;
        this.resource = book.getResources().getByIdOrHref(href);
    }


    public byte[] getResourceData() {
        byte[] data = new byte[0];
        if(resource==null)return null;
        try {
            data = resource.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

//    public byte[] getResourceData(String position) {
//        if (position == null || resource == null) {
//            return null;
//        }
//        String string = new String();
//        try {
//            string = new String(resource.getData(),StandardCharsets.UTF_8);
//            if (string.contains(position)) {
//                string = string.substring(string.indexOf(position) - 8);
//            }
//            if (StringUtil.appearNumber(string, "<h") > 1) {
//                string = string.substring(0, StringUtil.getFromIndex(string, "<h", 2));
//            } else {
//                string = string.substring(0, string.indexOf("</body>"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return string.getBytes(StandardCharsets.UTF_8);//   }


}
