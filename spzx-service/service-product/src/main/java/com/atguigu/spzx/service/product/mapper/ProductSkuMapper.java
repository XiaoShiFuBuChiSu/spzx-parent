package com.atguigu.spzx.service.product.mapper;

import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductSkuMapper {
    List<ProductSku> selectHotProductSkus();

    ProductSku selectById(Long skuId);

    List<ProductSku> selectByProductId(Long productId);
}
