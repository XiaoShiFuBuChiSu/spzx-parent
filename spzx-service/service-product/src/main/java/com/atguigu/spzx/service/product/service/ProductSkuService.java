package com.atguigu.spzx.service.product.service;

import com.atguigu.spzx.model.entity.product.ProductSku;

public interface ProductSkuService {
    ProductSku getBySkuId(Long skuId);
}
