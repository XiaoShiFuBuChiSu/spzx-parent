package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.vo.product.ProductSpecVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductSpecService {
    PageInfo<ProductSpecVo> queryPage(Integer current, Integer limit);

    boolean save(ProductSpecVo productSpecVo);

    boolean remove(Long id);

    boolean edit(ProductSpecVo productSpecVo);

    List<ProductSpecVo> findAll();
}
