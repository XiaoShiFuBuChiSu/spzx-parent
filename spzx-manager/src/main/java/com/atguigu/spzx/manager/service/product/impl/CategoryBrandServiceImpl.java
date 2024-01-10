package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.manager.mapper.product.CategoryBrandMapper;
import com.atguigu.spzx.manager.service.product.CategoryBrandService;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.product.CategoryBrandVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Autowired
    private CategoryBrandMapper categoryBrandMapper;

    @Override
    public PageInfo<CategoryBrandVo> queryPage(Integer current, Integer limit, CategoryBrand categoryBrand) {
        PageHelper.startPage(current, limit);
        List<CategoryBrandVo> list = categoryBrandMapper.selectPage(categoryBrand);
        return new PageInfo<>(list);
    }

    @Override
    public boolean save(CategoryBrand categoryBrand) {
        int res = categoryBrandMapper.insert(categoryBrand);
        return res > 0;
    }

    @Override
    public boolean edit(CategoryBrand categoryBrand) {
        int res = categoryBrandMapper.update(categoryBrand);
        return res>0;
    }

    @Override
    public boolean removeById(Long id) {
        int res = categoryBrandMapper.deleteById(id);
        return res>0;
    }
}
