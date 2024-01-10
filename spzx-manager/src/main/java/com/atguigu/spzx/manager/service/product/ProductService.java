package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.dto.product.ProductSaveDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    PageInfo<Product> queryPage(Integer current, Integer limit, ProductDto productDto);

    boolean save(ProductSaveDto productSaveDto);
}
