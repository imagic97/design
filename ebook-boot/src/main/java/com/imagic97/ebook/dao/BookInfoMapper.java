package com.imagic97.ebook.dao;

import com.imagic97.ebook.dto.BookInfoDTO;
import com.imagic97.ebook.entity.BookInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author imagic
 */
@Mapper
public interface BookInfoMapper {
    @Select("select * from book_info where book_id = #{bookId}")
    BookInfo selectBookInfoById(long bookId);

    @Select("select bi.*,b.file_name from book_info bi,book b where b.book_id in ( " +
            "select book_id from (select book_id from book limit #{offset},#{num}) as tamp ) " +
            "AND bi.book_id = b.book_id")
    List<BookInfoDTO> selectBookInfoList(@Param("num") int num,@Param("offset") int offset);

    //添加书籍信息
    @Insert("insert into book_info(book_id,title,introduction,language,publisher,creator,ISBN,publish_date)" +
            " value(#{bookId},#{title},#{introduction},#{language},#{publisher},#{creator},#{ISBN},#{publishDate})")
    @Options(useGeneratedKeys = true, keyColumn = "book_info_id", keyProperty = "bookInfoId")
    Integer addBookInfo(BookInfo bookInfo);

    //修改bookInfo信息
    @Update("update book_info set " +
            "title = #{title}," +
            "introduction = #{introduction}," +
            "language =#{language}," +
            "publisher = #{publisher}," +
            "creator=#{creator} " +
            "where book_info_id = #{bookInfoId}")
    Integer modifyBookInfo(BookInfo bookInfo);

    //根据bookId删除bookinfo
    @Delete("delete from book_info where book_info_id = #{bookInfoId}")
    Integer deleteBookInfoById(long bookInfoId);

    @Delete("delete from book_info where book_id = #{bookId}")
    Integer deleteBookInfoByBookId(long bookId);

//    @Select("select * from book_info bi,book b where title LIKE '%${param1}%'  or introduction LIKE '%${param1}%'  or " +
//            "creator LIKE '%${param1}%'  or ISBN LIKE '%${param1}%'  or publisher AND b.book_id = bi.book_id  LIKE '%${param1}%'" +
//            "limit #{offset},10")
//    List<BookInfoDTO> searchBook(@Param("param1") String param1, @Param("offset") int offset);

//    @Select("select * from book_info bi,book b  where bi.title LIKE '%${word}%'  or bi.introduction LIKE '%${word}%'  or " +
//            "bi.creator LIKE '%${word}%'  or bi.ISBN LIKE '%${word}%'  or bi.publisher  LIKE '%${word}%'" +
//            " AND b.book_id = bi.book_id")
//    List<BookInfoDTO> searchBook(@Param("word") String word, @Param("offset") int offset);

    @Select("select bi.*,b.file_name from book_info bi,book b where b.book_id in ( select book_id from (select book_id from book_info  where book_info.title LIKE '%${word}%'  or book_info.introduction LIKE '%${word}%'  or " +
            "book_info.creator LIKE '%${word}%'  or book_info.ISBN LIKE '%${word}%'  or book_info.publisher  LIKE '%${word}%'" +
            " limit #{offset},10) as tamp ) AND bi.book_id = b.book_id")
    List<BookInfoDTO> searchBook(@Param("word") String word, @Param("offset") int offset);

}
