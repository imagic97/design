package com.imagic97.ebook.dao;

import com.imagic97.ebook.dto.BookInfoDTO;
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
    Book selectBookById(long bookId);

    //添加书籍，bookName对于资源文件夹下的书名

    @Insert("insert into book(user_id,file_name,book_category,is_share,upload_date)" +
            " value(#{userId},#{fileName},#{bookCategory},#{isShare},#{uploadDate})")
    @Options(useGeneratedKeys = true, keyColumn = "book_id", keyProperty = "bookId")
    Integer addBook(Book book);

    //修改book信息
    @Update("update book set " +
            " file_name = #{fileName} " +
            "book_category = #{bookCategory} " +
            "is_share=#{isShare} " +
            "where book_id = #{bookId}")
    Integer modifyBookAll(Book book);

    @Update("update book set " +
            "book_category = #{bookCategory}, " +
            "is_share = #{isShare} " +
            "where book_id = #{bookId}")
    Integer modifyBook(@Param("bookId") long bookId, @Param("bookCategory") Integer category, @Param("isShare") Integer isShare);

    //根据bookId删除book
    @Delete("delete from book where book_id = #{bookId}")
    Integer deleteBookById(@Param("bookId") long bookId);

    //根据用户id查找书
    @Select("select * from book where user_id = #{userId}")
    List<Book> selectBookByUserId(long userId);
//
//    @Select("select * from book where book_category = #{CategoryId} AND is_Share = 1")
//    List<Book> selectBookByCategoryId(int CategoryId);

    @Select("select bi.*,b.file_name from book_info bi,book b where b.book_id in ( select book_id from book where book_category = #{CategoryId} AND is_Share = 1) AND bi.book_id = b.book_id")
    List<BookInfoDTO> selectBookByCategoryId(@Param("CategoryId") int CategoryId);
}
