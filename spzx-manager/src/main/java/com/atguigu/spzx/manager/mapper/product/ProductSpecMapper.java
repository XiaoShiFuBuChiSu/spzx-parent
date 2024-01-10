package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.vo.product.ProductSpecVo;

import java.util.List;

/**
* @author 19556
* @description 针对表【product_spec(商品规格)】的数据库操作Mapper
* @createDate 2024-01-10 11:37:43
* @Entity com.atguigu.spzx.model.entity.product.ProductSpec
*/
public interface ProductSpecMapper {

    List<ProductSpecVo> selectPage();

    int insert(ProductSpecVo productSpecVo);

    int delete(Long id);

    int update(ProductSpecVo productSpecVo);
}




