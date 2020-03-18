package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.Book;
import com.imagic97.ebook.entity.BookInfo;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.epub.Reader;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.BookInfoService;
import com.imagic97.ebook.services.BookService;
import com.imagic97.ebook.util.FileOperate;
import com.imagic97.ebook.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @author imagic
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    private BookService bookService;

    @Resource
    private BookInfoService bookInfoService;

    @Value("${BOOK_PATH}")
    private String BOOK_PATH;

    /**
     * 用户上传文件
     *
     * @param multipartFile 上传文件
     * @param httpSession   session会话
     */
    @RequestMapping("/upload")
    public ResultBody bookUpload(@RequestParam MultipartFile multipartFile, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        //   String path = getClass().getResource("/").getPath() + "static/book/";
        long timeStamp = System.currentTimeMillis();
        //long数据自提升为string
        String fileName = timeStamp + "_" + user.getUserId();
        //保存上传文件至本地，返回文件名
        boolean isDone = FileOperate.fileUpload(multipartFile, BOOK_PATH, fileName);
        Book book = null;
        BookInfo bookInfo;
        Integer flag = -1;
        try {
            if (isDone) {
                //书籍信息初始化
                book = Book.builder()
                        .userId(user.getUserId())
                        .uploadDate(StringUtil.getCurrentTimeStampWithLong(timeStamp))
                        .fileName(fileName)
                        .bookCategory(-1)
                        .isShare(0)
                        .build();
                flag = bookService.addBook(book);
                Reader reader = new Reader(BOOK_PATH + fileName);
                //书籍详细信息初始化
                bookInfo = BookInfo.builder()
                        .bookId(book.getBookId())
                        .creator(reader.getAuthor())
                        .Introduction(reader.getIntroduction())
                        .ISBN(reader.getISBN())
                        .language(reader.getLanguage())
                        .publishDate(reader.getPublishDate())
                        .publisher(reader.getPublisher())
                        .title(reader.getTitle()).build();
                bookInfoService.addBookInfo(bookInfo);
            }
        } catch (Exception e) {
            //数据库读写失败
            if (book != null && book.getBookId() != 0) {
                //删除文件
                FileOperate.deleteFile(BOOK_PATH + fileName);
                //清楚数据库相关记录
                if (flag > -1) {
                    bookService.deleteBookById(book.getBookId(), user.getUserId());
                }
            }
            e.printStackTrace();
            throw new MessageException("上传文件错误");
        }
        return ResultBody.success(null);
    }

    @RequestMapping(value = "/delete")
    public ResultBody deleteBookByUser(@RequestParam long bookId,
                                       @RequestParam String fileName,
                                       HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (FileOperate.deleteFile(BOOK_PATH + fileName))
            bookService.deleteBookById(bookId, user.getUserId());
        return ResultBody.success(null);
    }
}
