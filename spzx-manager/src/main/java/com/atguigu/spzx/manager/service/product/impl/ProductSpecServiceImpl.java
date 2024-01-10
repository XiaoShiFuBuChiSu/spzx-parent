package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.manager.mapper.product.ProductSpecMapper;
import com.atguigu.spzx.manager.service.product.ProductSpecService;
import com.atguigu.spzx.model.vo.product.ProductSpecVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductSpecServiceImpl implements ProductSpecService {
    @Autowired
    private ProductSpecMapper productSpecMapper;

    @Override
    public PageInfo<ProductSpecVo> queryPage(Integer current, Integer limit) {
        PageHelper.startPage(current, limit);
        List<ProductSpecVo> list = productSpecMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public boolean save(ProductSpecVo productSpecVo) {
        int res = productSpecMapper.insert(productSpecVo);
        return res > 0;
    }

    @Override
    public boolean remove(Long id) {
        int res = productSpecMapper.delete(id);
        return res > 0;
    }

    @Override
    public boolean edit(ProductSpecVo productSpecVo) {
        int res = productSpecMapper.update(productSpecVo);
        return res > 0;
    }

    @Override
    public List<ProductSpecVo> findAll() {
        return productSpecMapper.selectAll();
    }
}
