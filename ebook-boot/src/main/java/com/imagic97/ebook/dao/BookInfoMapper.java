package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.BookInfo;
import org.apache.ibatis.annotations.*;

/**
 * @author imagic
 */
@Mapper
public interface BookInfoMapper {
    @Select("select * from book_info where book_id = #{bookId}")
    BookInfo selectBookInfoById(long bookId);

    //添加书籍信息
    @Insert("insert into book_info(book_id,title,introduction,language,publisher,creator,ISBN,publish_date)" +
            " value(#{bookId},#{title},#{introduction},#{language},#{publisher},#{creator},#{ISBN},#{publishDate})")
    @Options(useGeneratedKeys = true , keyColumn = "book_info_id", keyProperty = "bookInfoId")
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

}
