package com.atguigu.spzx.service.product.controller;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.product.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@RestController
@Tag(name = "前端页面分类接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findCategoryTree")
    public Result findCategoryTree() {
        List<Category> categoryTree = categoryService.getCategoryTree();
        return Result.build(categoryTree, ResultCodeEnum.SUCCESS);
    }
}
