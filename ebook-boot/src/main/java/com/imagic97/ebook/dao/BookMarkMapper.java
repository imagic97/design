package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.BookMark;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author imagic
 */
@Mapper
public interface BookMarkMapper {
    //添加书签
    @Insert("insert into book_mark(user_id,book_id,chapters,createDate) value (userId,bookId,chapters,createDate)")
    public Integer addBookMark(BookMark bookMark);

    //根据bookId查询书签
    @Select("select from book_mark where book_id = #{bookId}")
    public BookMark selectBookMarkByBookId(long bookId);

    //根据userId查询书签
    @Select("select from book_mark where user_id = #{userId}")
    public BookMark selectBookMarkByUserId(long userId);

    //删除书签
    @Delete("delete from book_mark where book_mark_id = #{bookMarkId}")
    public Integer deleteBookMarkById(long bookMarkId);
}
