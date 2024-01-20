package com.atguigu.spzx.service.product.service;

import com.atguigu.spzx.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategoryTree();
}
