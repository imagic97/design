package com.imagic97.ebook.dao;

import com.imagic97.ebook.dto.SelfBook;
import com.imagic97.ebook.entity.Self;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author imagic
 */
@Mapper
public interface SelfMapper {
    @Insert("insert into self(book_id,user_id,create_date) value(#{bookId},#{userId},#{createDate})")
    Integer addBookToSelf(Self self);

    @Delete(("delete from self where self_id = #{selfId} AND user_id = #{userId}"))
    Integer deleteSelf(@Param("selfId") long selfId, @Param("userId") long userId);

    @Delete(("delete from self where book_id = #{bookId} AND user_id = #{userId}"))
    Integer deleteSelfByBookId(@Param("bookId") long bookId, @Param("userId") long userId);

    @Select("SELECT s.self_id, b.book_id, b.file_name, bi.title " +
            "FROM book b,book_info bi,self s " +
            "where s.user_id = #{userId} AND b.book_id = s.book_id AND bi.book_id = s.book_id")
    List<SelfBook> selectSelfByUserId(long userId);

    @Select("SELECT b.book_id, b.file_name, bi.title " +
            "FROM book b,book_info bi " +
            "where b.user_id = #{userId} AND bi.book_id = b.book_id")
    List<SelfBook> selectBookByUserId(long userId);
}
