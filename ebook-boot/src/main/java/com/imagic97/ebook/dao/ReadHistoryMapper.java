package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.ReadHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author imagic
 */
@Mapper
public interface ReadHistoryMapper {
    @Insert("insert into read_history(user_id,book_id,chapter)" +
            " value(#{userId},#{bookId},#{chapter})")
    Integer addReadHistory(ReadHistory readHistory);

    @Select("select * from read_history where user_id = #{userId} order by update_date desc")
    List<ReadHistory> selectReadHistoryByUserId(long userId);

    @Select("select * from read_history where book_id = #{bookId}")
    ReadHistory selectReadHistoryByBookId(long bookId);

    @Delete("delete from read_history where read_history_id = #{readHistoryId}")
    Integer deleteReadHistoryById(long read_history_id);

    @Update("update read_history set" +
            " chapter = #{chapter}" +
            " where book_id = #{bookId} and user_id = #{userId}")
    Integer modifyReadHistory(ReadHistory readHistory);
}
