package com.atguigu.spzx.service.product.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductItemVo;
import com.atguigu.spzx.service.product.mapper.ProductDetailsMapper;
import com.atguigu.spzx.service.product.mapper.ProductMapper;
import com.atguigu.spzx.service.product.mapper.ProductSkuMapper;
import com.atguigu.spzx.service.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<ProductSku> page(Integer page, Integer limit, ProductDto productDto) {
        PageHelper.startPage(page, limit);
        List<ProductSku> productSkuList = productMapper.selectPage(productDto);
        PageInfo<ProductSku> pageInfo = new PageInfo<>(productSkuList);
        return pageInfo;
    }

    @Override
    public ProductItemVo getProductItem(Long skuId) {
        // 获取商品sku信息
        ProductSku productSku = productSkuMapper.selectById(skuId);
        if (ObjectUtils.isEmpty(productSku)) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }
        // 获取商品信息
        Long productId = productSku.getProductId();
        if (productId == null) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }

        Product product = productMapper.selectById(productId);
        // 获取商品轮播图列表
        String sliderUrls = product.getSliderUrls();
        List<String> sliderUrlList = new ArrayList<>();
        if (StringUtils.hasText(sliderUrls)) {
            List<String> list = Arrays.asList(sliderUrls.split(","));
            sliderUrlList.addAll(list);
        }
        // 获取商品详情图片列表
        String detailUrls = productDetailsMapper.selectDeatilUrlsByProductId(productId);
        List<String> detailUrlList = new ArrayList<>();
        if (StringUtils.hasText(detailUrls)) {
            List<String> list = Arrays.asList(detailUrls.split(","));
            detailUrlList.addAll(list);
        }
        // 获取商品规格信息
        JSONArray specValueList = JSONArray.parseArray(product.getSpecValue());
        // 获取商品规格对应商品skuId信息
        List<ProductSku> productSkuList = productSkuMapper.selectByProductId(productId);
        Map<String, Object> skuSpecValueMap = new HashMap<>();
        productSkuList.stream().forEach(sku -> skuSpecValueMap.put(sku.getSkuSpec(), sku.getId()));
        // 添加
        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setSliderUrlList(sliderUrlList);
        productItemVo.setDetailsImageUrlList(detailUrlList);
        productItemVo.setSpecValueList(specValueList);
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);

        return productItemVo;
    }
}
