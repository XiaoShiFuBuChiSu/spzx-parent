package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.manager.service.product.ProductUnitService;
import com.atguigu.spzx.manager.mapper.product.ProductUnitMapper;
import com.atguigu.spzx.model.vo.product.ProductUnitVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public PageInfo<ProductUnitVo> queryPage(Integer current, Integer limit) {
        PageHelper.startPage(current, limit);
        return new PageInfo<>(productUnitMapper.findAll());
    }

    @Override
    public boolean save(ProductUnitVo productUnitVo) {
        return productUnitMapper.save(productUnitVo) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return productUnitMapper.delete(id) > 0;
    }

    @Override
    public boolean edit(ProductUnitVo productUnitVo) {
        return productUnitMapper.update(productUnitVo);
    }
}
