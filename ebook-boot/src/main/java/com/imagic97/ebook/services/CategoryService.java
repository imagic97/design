package com.imagic97.ebook.services;

import com.imagic97.ebook.entity.Category;

import java.util.List;

/**
 * @author imagic
 */
public interface CategoryService {

    List<Category> selectAllCategory();

    Integer addCategory(Category category);

    Integer deleteCategoryById(long categoryId);
}
