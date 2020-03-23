package com.imagic97.ebook.controller;

import com.imagic97.ebook.common.ResultBody;
import com.imagic97.ebook.entity.Category;
import com.imagic97.ebook.entity.User;
import com.imagic97.ebook.services.BookService;
import com.imagic97.ebook.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 电子书分类
 *
 * @author imagic
 */
@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private BookService bookService;

    @GetMapping("/get_category")
    @ApiOperation("获取分类列表")
    public ResultBody getCategory() {
        return ResultBody.success(categoryService.selectAllCategory());
    }

    @GetMapping("/get_category_book")
    @ApiOperation("获取分类下的电子书")
    public ResultBody getCategoryBook(@RequestParam int category_Id) {
        return ResultBody.success(bookService.selectBookById(category_Id));
    }

    @GetMapping("/add_category")
    @ApiOperation("增加分类列表")
    public ResultBody addCategory(@RequestParam String categoryName, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        //判断是否为普通用户
        if (user.getType() == 1) {
            return ResultBody.error("删除失败,无权限");
        }
        Category category = Category.builder().categoryName(categoryName).build();
        if (categoryService.addCategory(category) > 0) {
            return ResultBody.success(null);
        }
        return ResultBody.error("添加失败");
    }

    @GetMapping("/delete_category")
    @ApiOperation("删除分类")
    public ResultBody deleteCategory(@RequestParam int categoryId, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        //判断是否为普通用户
        if (user.getType() == 1) {
            return ResultBody.error("删除失败,无权限");
        }
        if (categoryService.deleteCategoryById(categoryId) > 0) {
            return ResultBody.success(null);
        }
        return ResultBody.error("添加失败");
    }
}
