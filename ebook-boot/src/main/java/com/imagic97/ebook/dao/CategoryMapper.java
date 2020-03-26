package com.imagic97.ebook.dao;

import com.imagic97.ebook.entity.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
