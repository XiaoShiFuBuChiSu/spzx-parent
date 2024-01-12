package com.atguigu.spzx.manager.service.product;

import com.atguigu.spzx.model.dto.product.ProductInfoDto;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.product.ProductInfoVo;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    PageInfo<Product> queryPage(Integer current, Integer limit, ProductDto productDto);

    boolean save(ProductInfoDto productSaveDto);

    ProductInfoVo getById(Long id);

    boolean editById(ProductInfoDto productInfoDto);

    boolean removeById(Long id);

    int changeAuditStatus(Long id, Integer auditStatus);

    int changeStatus(Long id, Integer status);
}
