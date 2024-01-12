package com.atguigu.spzx.manager.service.product.impl;

import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.manager.mapper.product.ProductDetailsMapper;
import com.atguigu.spzx.manager.mapper.product.ProductMapper;
import com.atguigu.spzx.manager.mapper.product.ProductSkuMapper;
import com.atguigu.spzx.manager.service.product.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDetailsDto;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.dto.product.ProductInfoDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.entity.product.ProductDetails;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductInfoVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
    public boolean save(ProductInfoDto productInfoDto) {
        // 添加到产品信息表
        Product product = new Product();
        BeanUtils.copyProperties(productInfoDto, product);
        int result = productMapper.save(product);
        if (product.getId() == null || result <= 0) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }
        // 获取生成的productId
        Long productId = product.getId();
        // 添加到详情
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(productId);
        productDetails.setImageUrls(productInfoDto.getDetailsImageUrls());
        int detailResult = productDetailsMapper.save(productDetails);
        if (detailResult <= 0) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }

        // 添加到sku
        List<ProductSku> productSkus = productInfoDto.getProductSkuList();
        // skuCode和skuName需要自己添加
        for (int i = 0; i < productSkus.size(); i++) {
            ProductSku productSku = productSkus.get(i);
            productSku.setSkuCode(productId + "_" + (i + 1));
            productSku.setSkuName(product.getName() + " " + productSku.getSkuSpec());
            productSku.setProductId(productId);
        }
        int skuResult = productSkuMapper.saveBatch(productSkus);
        if (skuResult <= 0) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }
        return true;
    }

    @Override
    public ProductInfoVo getById(Long id) {
        // 获取商品
        ProductInfoDto productInfoDto = productMapper.getById(id);
        if (ObjectUtils.isEmpty(productInfoDto)) {
            throw new GlobalResultException(ResultCodeEnum.NETWORK_ERROR);
        }
        // 获取商品详情
        ProductDetailsDto productDetailsDto = productDetailsMapper.getByProductId(id);
        productInfoDto.setDetailsImageUrls(productDetailsDto.getImageUrls());
        // 获取商品Sku
        List<ProductSku> productSkuList = productSkuMapper.getProductSkuListByProductId(id);
        productInfoDto.setProductSkuList(productSkuList);
        ProductInfoVo productInfoVo = new ProductInfoVo();
        BeanUtils.copyProperties(productInfoDto, productInfoVo);
        return productInfoVo;
    }

    @Override
    public boolean editById(ProductInfoDto productInfoDto) {
        // 更新product
        Product product = new Product();
        BeanUtils.copyProperties(productInfoDto, product);
        int res = productMapper.update(product);
        // 更新productDetails
        ProductDetails productDetails = new ProductDetails();
        productDetails.setImageUrls(productInfoDto.getDetailsImageUrls());
        productDetails.setProductId(productInfoDto.getId());
        int resDetail = productDetailsMapper.update(productDetails);
        // 更新productSku
        List<ProductSku> productSkus = productInfoDto.getProductSkuList();
        productSkus.forEach(productSku -> productSkuMapper.update(productSku));
        return true;
    }

    @Override
    public boolean removeById(Long id) {
        // 删除商品
        int res = productMapper.delete(id);
        // 删除商品详情
        int resDetail = productDetailsMapper.deleteByProductId(id);
        // 删除商品SKU
        int resSku = productSkuMapper.deleteByProductId(id);
        return true;
    }

    @Override
    public int changeAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        product.setAuditStatus(auditStatus);
        product.setAuditMessage(auditStatus == 1 ? "审核通过" : "驳回申请");
        return productMapper.update(product);
    }

    @Override
    public int changeStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        return productMapper.update(product);
    }

}
