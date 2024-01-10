package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.manager.mapper.product.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.product.ProductMapper;
import com.atguigu.spzx.manager.mapper.product.ProductSkuMapper;
import com.atguigu.spzx.manager.service.product.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.dto.product.ProductSaveDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public PageInfo<Product> queryPage(Integer current, Integer limit, ProductDto productDto) {
        PageHelper.startPage(current, limit);
        List<Product> list = productMapper.selectPage(productDto);
        return new PageInfo<>(list);
    }

    @Override
    public boolean save(ProductSaveDto productSaveDto) {
        // 添加到产品信息表
        Product product = new Product();
        BeanUtils.copyProperties(productSaveDto, product);
        int result = productMapper.save(product);
        if (product.getId() == null || result <= 0) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }
        // 获取生成的productId
        Long productId = product.getId();
        // 添加到详情
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(productId);
        productDetails.setImageUrls(productSaveDto.getDetailsImageUrls());
        int detailResult = productDetailsMapper.save(productDetails);
        if (detailResult <= 0) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }

        // 添加到sku
        List<ProductSku> productSkus = productSaveDto.getProductSkuList();
        // skuCode和skuName需要自己添加
        for (int i = 0; i < productSkus.size(); i++) {
            ProductSku productSku = productSkus.get(i);
            productSku.setSkuCode(productId + "_" + (i + 1));
            productSku.setSkuName(product.getName()+" "+productSku.getSkuSpec());
            productSku.setProductId(productId);
        }
        int skuResult = productSkuMapper.saveBatch(productSkus);
        if (skuResult <= 0) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }
        return true;
    }
}
