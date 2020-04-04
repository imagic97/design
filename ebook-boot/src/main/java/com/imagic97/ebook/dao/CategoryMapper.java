package com.imagic97.ebook.dao;

import com.imagic97.ebook.dto.BookInfoDTO;
import com.imagic97.ebook.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author imagic
 */
@Mapper
public interface CategoryMapper {
    //获取所有分类
    @Select("select * from category")
    List<Category> selectAllCategory();

    //添加分类
    @Insert("insert into category(category_name) value (#{categoryName})")
    Integer addCategory(Category category);

    //删除分类，不可取
    @Delete("delete from category where category_id =#{categoryId}")
    Integer deleteCategoryById(long categoryId);

    @Select("select bi.*,b.file_name from book_info bi,book b where b.book_id in ( select book_id from (select book_id from book_info  where book_info.title LIKE '%${word}%'  or book_info.introduction LIKE '%${word}%'  or " +
            "book_info.creator LIKE '%${word}%'  or book_info.ISBN LIKE '%${word}%'  or book_info.publisher  LIKE '%${word}%'" +
            " limit #{offset},10) as tamp ) AND bi.book_id = b.book_id")
    List<BookInfoDTO> searchBook(@Param("word") String word, @Param("offset") int offset);
}
