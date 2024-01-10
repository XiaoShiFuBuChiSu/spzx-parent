package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.product.CategoryBrandVo;
import com.github.pagehelper.PageInfo;

public interface CategoryBrandService {
    PageInfo<CategoryBrandVo> queryPage(Integer current, Integer limit, CategoryBrand categoryBrand);

    boolean save(CategoryBrand categoryBrand);

    boolean edit(CategoryBrand categoryBrand);

    boolean removeById(Long id);
}
