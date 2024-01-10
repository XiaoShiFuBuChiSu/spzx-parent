package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.entity.product.ProductDetails;

/**
* @author 19556
* @description 针对表【product_details(商品sku属性表)】的数据库操作Mapper
* @createDate 2024-01-10 19:48:03
* @Entity com.atguigu.spzx.model.entity.product.ProductDetails
*/
public interface ProductDetailsMapper {

    int save(ProductDetails productDetails);
}




