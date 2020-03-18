package com.imagic97.ebook.epub;

import com.imagic97.ebook.exception.MessageException;
import net.sf.jazzlib.ZipFile;
import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.EpubReader;
import nl.siegmann.epublib.service.MediatypeService;

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
            throw new MessageException("0", "资源不存在");
        }
    }

    public Reader(String bookFile, String href) {
        this(bookFile);
        this.bookFile = bookFile;
        this.href = href;
        this.resource = book.getResources().getByIdOrHref(href);
    }


    public byte[] getResourceData() {
        byte[] result = new byte[0];
        if (resource == null) return null;
        try {
            result = resource.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getCSS() {
        List<Resource> resourceList = this.book.getResources().getResourcesByMediaType(MediatypeService.CSS);
        StringBuilder result = new StringBuilder();
        try {
            for (Resource item : resourceList) {
                result.append(new String(item.getData()));
            }
        } catch (Exception e) {
            throw new MessageException("0", "读取样式过程出现错误");
        }
        return result.toString();
    }

    public String getTitle() {
        return book.getMetadata().getFirstTitle();
    }

    public String getAuthor() {
        List<Author> authors = book.getMetadata().getAuthors();
        StringBuilder result = new StringBuilder();
        for (Author author : authors) {
            result.append(author.getFirstname()).append(" ").append(author.getLastname()).append("/");
        }
        return result.toString();
    }

    public String getIntroduction() {
        List<String> stringList = book.getMetadata().getDescriptions();
        StringBuilder result = new StringBuilder();
        for (String string : stringList) {
            result.append(string).append(" ");
        }
        return result.toString();
    }

    public String getLanguage() {
        return book.getMetadata().getLanguage();
    }

    /**
     * 获取出版社
     *
     * @return 出版社字符串
     */
    public String getPublisher() {
        List<String> stringList = book.getMetadata().getPublishers();
        StringBuilder result = new StringBuilder();
        for (String string : stringList) {
            result.append(string).append(" ");
        }
        return result.toString();
    }

    public String getISBN() {
        List<Identifier> identifierList = book.getMetadata().getIdentifiers();
        for (Identifier identifier : identifierList) {
            if (identifier.getScheme().equals("ISBN"))
                return identifier.getValue();
        }
        return "";
    }

    public String getPublishDate() {
        List<Date> dateList = book.getMetadata().getDates();
        if (dateList == null) return "";
        for (Date date : dateList) {
            if (date.getEvent() == Date.Event.PUBLICATION)
                return date.getValue();
        }
        if (dateList.size() == 1) return dateList.get(0).getValue();
        return null;
    }

}
