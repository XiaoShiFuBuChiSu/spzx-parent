package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.product.BrandVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BrandService {
    PageInfo<Brand> getPage(Integer current, Integer limit);

    boolean save(Brand brand);

    boolean remove(Long id);

    Brand getById(Long id);

    boolean edit(Brand brand);

    List<BrandVo> findAll(Map<String, Object> map);
}
