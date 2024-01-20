package com.atguigu.spzx.service.product.service.impl;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.service.product.IndexVo;
import com.atguigu.spzx.service.product.mapper.CategoryMapper;
import com.atguigu.spzx.service.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.service.product.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public IndexVo getIndexData() {
        IndexVo indexVo = new IndexVo();
        // 获取首页要展示的分类列表
        List<Category> categoryList = categoryMapper.selectRootCategories();
        // 获取首页要展示的商品列表
        List<ProductSku> productSkuList = productSkuMapper.selectHotProductSkus();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return indexVo;
    }
}
