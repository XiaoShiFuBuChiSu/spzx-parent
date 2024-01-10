package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.manager.service.product.ProductUnitService;
import com.atguigu.spzx.manager.mapper.product.ProductUnitMapper;
import com.atguigu.spzx.model.vo.product.ProductUnitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnitVo> findAll() {
        return productUnitMapper.findAll();
    }
}
