package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.BookComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author imagic
 */
public interface BookCommentMapper {
    //添加书评
    @Insert("insert into book_comment(user_id,content,createDate) value(#{userId},#{content},#{createDate})")
    public Integer addComment(BookComment bookComment);

    //删除书评
    @Delete("delete from book_comment where comment_id = #{commentId}")
    public Integer deleteCommentById(long commentId);

    //修改书评
    @Update("update book_comment set content=#{content}")
    public Integer modifyComment(String content);

    //查询书评（bookId）
    @Select("select * from book_comment where book_id =#{bookId}")
    public BookComment selectCommentByBookId(long bookId);

    //查询书评（userId）
    @Select("select * from book_comment where user_id =#{userId}")
    public BookComment selectCommentByUserId(long userId);

}
