package com.imagic97.ebook.services.impl;

import com.imagic97.ebook.dao.CategoryMapper;
import com.imagic97.ebook.entity.Category;
import com.imagic97.ebook.services.CategoryService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author imagic
 */
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> selectAllCategory() {
        return categoryMapper.selectAllCategory();
    }

    @Override
    public Integer addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    @Override
    public Integer deleteCategoryById(long categoryId) {
        return categoryMapper.deleteCategoryById(categoryId);
    }
}
