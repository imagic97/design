package com.imagic97.ebook.controller;

import com.imagic97.ebook.annotation.PassToken;
import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.Book;
import com.imagic97.ebook.entity.BookInfo;
import com.imagic97.ebook.entity.Self;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.epub.Reader;
import com.imagic97.ebook.exception.MessageException;
import com.imagic97.ebook.services.BookInfoService;
import com.imagic97.ebook.services.BookMarkService;
import com.imagic97.ebook.services.BookService;
import com.imagic97.ebook.services.SelfService;
import com.imagic97.ebook.util.FileOperate;
import com.imagic97.ebook.util.StringUtil;
import com.imagic97.ebook.util.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author imagic
 */
@RestController
@RequestMapping("/book")
@ApiOperation("电子书操作模块")
public class BookController {

    @Resource
    private BookService bookService;

    @Resource
    private SelfService selfService;

    @Resource
    private BookMarkService bookMarkService;

    @Resource
    private BookInfoService bookInfoService;

    @Value("${BOOK_PATH}")
    private String BOOK_PATH;

    /**
     * 用户上传文件
     *
     * @param multipartFile 上传文件
     */
    @PostMapping(value = "/upload")
    @ApiOperation(value = "用户上传书本", response = ResultBody.class)
    public ResultBody bookUpload(@RequestParam MultipartFile multipartFile) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        //   String path = getClass().getResource("/").getPath() + "static/book/";
        long timeStamp = System.currentTimeMillis();
        //long数据自提升为string
        String fileType = multipartFile.getContentType();
        if (!"application/epub+zip".contains(fileType)) {
            return ResultBody.error("格式错误,该文件类型为：" + fileType);
        }
        String fileName = timeStamp + "_" + user.getUserId();
        //保存上传文件至本地，返回文件名
        boolean isDone = FileOperate.fileUpload(multipartFile, BOOK_PATH, fileName);
        Book book = null;
        BookInfo bookInfo = null;
        Self self;
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
//
//                //上传的书存至self表
//                //判断是否用户
//                if (user.getType() == 1) {
//                    self = Self.builder()
//                            .userId(user.getUserId())
//                            .bookId(book.getBookId())
//                            .createDate(StringUtil.getCurrentTimeStampWithLong(timeStamp)).build();
//                    selfService.addBookToSelf(self);
//                }
            }
        } catch (Exception e) {
            //数据库读写失败
            if (book != null && book.getBookId() != 0) {
                //删除文件
                FileOperate.deleteFile(BOOK_PATH + fileName);
                //清楚数据库相关记录
                if (flag > -1) {
                    bookService.deleteBookById(book.getBookId());
                }
            }
            e.printStackTrace();
            throw new MessageException("上传文件错误");
        }
        return ResultBody.success(bookInfo);
    }

    @GetMapping(value = "/delete")
    @ApiOperation("删除电子书")
    public ResultBody deleteBookByUser(@RequestParam long bookId,
                                       @RequestParam String fileName) {
        User user = (User) TokenUtil.getSession().getAttribute("user");

        Book currentBook = bookService.selectBookById(bookId);
        if (currentBook == null) {
            return ResultBody.error("异常操作");
        }
        if (!currentBook.getFileName().equals(fileName)) {
            return ResultBody.error("异常操作");
        }

        //判断用户是否自己上传的书
        if (currentBook.getUserId() != user.getUserId()) {
            //判断是否为普通用户
            if (user.getType() == 1) {
                return ResultBody.error("删除失败,无权限");
            }
        }
        if (FileOperate.deleteFile(BOOK_PATH + fileName)) {
            bookService.deleteBookById(bookId);
        }
        return ResultBody.success(null);
    }

    @PassToken
    @GetMapping("/getBookInfo")
    @ApiOperation("获取电子书详细信息")
    public ResultBody getBookInfoByBookId(@RequestParam long bookId) {
        return ResultBody.success(bookInfoService.selectBookInfoById(bookId));
    }

    @GetMapping("/modifyBook")
    @ApiOperation("修改电子书分类以及是否可分享 ---管理员")
    public ResultBody modifyBook(
            @RequestParam long bookId,
            @RequestParam Integer bookCategory,
            @RequestParam Integer isShare) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        if (user.getType() == 1) return ResultBody.error("用户无权限");
        if (bookService.modifyBook(bookId, bookCategory, isShare) > 0) {
            return ResultBody.success(null);
        }
        return ResultBody.error("修改失败");
    }

    @PostMapping("/modifyBookInfo")
    @ApiOperation("修改电子书详细信息 ---管理员/用户")
    public ResultBody modifyBookInfo(
            @RequestBody BookInfo bookInfo) {
        User user = (User) TokenUtil.getSession().getAttribute("user");
        Book currentBook = bookService.selectBookById(bookInfo.getBookId());
        if (currentBook.getUserId() != user.getUserId()) {
            //判断是否为普通用户
            if (user.getType() == 1) {
                return ResultBody.error("删除失败,无权限");
            }
        }
        if (bookInfoService.modifyBookInfo(bookInfo) > 0) {
            return ResultBody.success(null);
        }
        return ResultBody.error("修改失败，请稍后重试！");
    }


}
