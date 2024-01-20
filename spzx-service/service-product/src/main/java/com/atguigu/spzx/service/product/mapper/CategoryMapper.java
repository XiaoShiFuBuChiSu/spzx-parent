package com.atguigu.spzx.service.product.mapper;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> selectRootCategories();

    List<Category> selectAll();
}
