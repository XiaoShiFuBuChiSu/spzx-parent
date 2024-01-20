package com.atguigu.spzx.service.product.service;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.product.ProductItemVo;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    PageInfo<ProductSku> page(Integer page, Integer limit, ProductDto productDto);

    ProductItemVo getProductItem(Long skuId);
}
