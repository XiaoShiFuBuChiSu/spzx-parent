package com.atguigu.spzx.service.product.mapper;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

public interface ProductMapper {
    List<ProductSku> selectPage(ProductDto productDto);

    Product selectById(Long productId);
}
