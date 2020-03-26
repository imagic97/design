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

    @Select("select from read_history where user_id = #{userId}")
    List<ReadHistory> selectReadHistoryByUserId(String userId);

    @Delete("delete from read_history where read_history_id = #{readHistoryId}")
    Integer deleteReadHistoryById(String bookId);

    @Update("update read_history set" +
            " chapter = #{chapter}" +
            " where read_history_id = #{readHistoryId}")
    Integer modifyReadHistory(ReadHistory readHistory);
}
