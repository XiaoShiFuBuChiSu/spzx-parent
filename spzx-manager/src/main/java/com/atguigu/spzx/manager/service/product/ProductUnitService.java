package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.vo.product.ProductUnitVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductUnitService {
    List<ProductUnitVo> findAll();

    PageInfo<ProductUnitVo> queryPage(Integer current, Integer limit);

    boolean save(ProductUnitVo productUnitVo);

    boolean remove(Long id);

    boolean edit(ProductUnitVo productUnitVo);
}
