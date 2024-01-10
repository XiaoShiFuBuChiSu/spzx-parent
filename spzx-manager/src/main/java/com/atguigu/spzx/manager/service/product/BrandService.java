package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> getPage(Integer current, Integer limit);

    boolean save(Brand brand);

    boolean remove(Long id);

    Brand getById(Long id);

    boolean edit(Brand brand);

    List<Brand> findAll();
}
