package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author imagic
 */
@Mapper
public interface BookMapper {
    //根据bookId 查找书
    @Select("select * from book where book_id = #{bookId}")
    public Book selectBookById(long bookId);

    //添加书籍，bookName对于资源文件夹下的书名
    @Insert("insert into book(user_id,book_name,file_name,is_share,upload_date) value(userId,bookName,fileName,isShare,uploadDate)")
    public Integer addBook(Book book);

    //修改book信息
    @Update("update book set" +
            " book_name=#{bookName}," +
            " author = #{author}" +
            " introduction = #{introduction}" +
            " publisher =#{publisher}" +
            "book_category = #{bookCategory}" +
            "is_share=#{isShare},")
    public Integer modifyBook(Book book);

    //根据bookId删除book
    @Delete("delete from book where book_id = #{bookId}")
    public Integer deleteBookById(String bookId);

    //根据bookName相似名查找书
    @Select("select from book where book_name like '%#{bookName}%'")
    public List<Book> selectBookByBookName(String bookName);

    //根据作者查找书
    @Select("select from book where author = #{author}")
    public List<Book> selectBookByAuthor(String author);

    //根据用户id查找书
    @Select("select from book where user_id = #{userId}")
    public List<Book> selectBookByUserId(String userId);

}
