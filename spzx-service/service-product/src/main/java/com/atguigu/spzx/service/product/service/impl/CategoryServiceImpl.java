package com.atguigu.spzx.service.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.service.product.mapper.CategoryMapper;
import com.atguigu.spzx.service.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Cacheable(value = "categoryTree", key = "'all'")
    public List<Category> getCategoryTree() {
        // 获取所有分类菜单
        List<Category> list = categoryMapper.selectAll();
        // 获取一级菜单
        List<Category> firstCategories = list.stream().filter(category -> category.getParentId() == 0).toList();
        // 获取二级菜单
        firstCategories.forEach(firstCategory -> {
            List<Category> secondCategories = list.stream().filter(secondCategory -> secondCategory.getParentId() == firstCategory.getId()).toList();
            secondCategories.forEach(secondCategory -> {
                // 获取三级菜单
                List<Category> thirdCategories = list.stream().filter(thirdCategory -> thirdCategory.getParentId() == secondCategory.getId()).toList();
                secondCategory.setChildren(thirdCategories);
            });
            firstCategory.setChildren(secondCategories);
        });

        return firstCategories;
    }
}
