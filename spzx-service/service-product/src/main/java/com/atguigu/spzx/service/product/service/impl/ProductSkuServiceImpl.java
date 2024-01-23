package com.atguigu.spzx.service.product.service.impl;

import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.service.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.service.product.service.ProductSkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductSkuServiceImpl implements ProductSkuService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public ProductSku getBySkuId(Long skuId) {
        ProductSku productSku = productSkuMapper.selectById(skuId);
        return productSku;
    }
}
