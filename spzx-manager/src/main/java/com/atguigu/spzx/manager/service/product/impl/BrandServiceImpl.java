package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.manager.mapper.product.BrandMapper;
import com.atguigu.spzx.manager.service.product.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageInfo<Brand> getPage(Integer current, Integer limit) {
        PageHelper.startPage(current, limit);
        PageInfo<Brand> pageInfo = new PageInfo<>(brandMapper.selectPage());
        return pageInfo;
    }

    @Override
    public boolean save(Brand brand) {
        int res = brandMapper.insert(brand);
        return res > 0;
    }

    @Override
    public boolean remove(Long id) {
        int res = brandMapper.delete(id);
        return res > 0;
    }

    @Override
    public Brand getById(Long id) {
        return brandMapper.selectById(id);
    }

    @Override
    public boolean edit(Brand brand) {
        int res = brandMapper.update(brand);
        return false;
    }

    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

}
