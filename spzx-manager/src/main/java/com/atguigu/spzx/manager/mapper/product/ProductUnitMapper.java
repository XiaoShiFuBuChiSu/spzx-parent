package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.vo.product.ProductUnitVo;

import java.util.List;

/**
* @author 19556
* @description 针对表【product_unit(商品单位)】的数据库操作Mapper
* @createDate 2024-01-10 16:43:29
* @Entity com.atguigu.spzx.model.entity.product.ProductUnit
*/
public interface ProductUnitMapper {

    List<ProductUnitVo> findAll();

    int save(ProductUnitVo productUnitVo);

    int delete(Long id);

    boolean update(ProductUnitVo productUnitVo);
}




