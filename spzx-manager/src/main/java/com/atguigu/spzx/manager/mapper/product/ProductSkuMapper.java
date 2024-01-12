package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.entity.product.ProductSku;

import java.util.List;

/**
* @author 19556
* @description 针对表【product_sku(商品sku)】的数据库操作Mapper
* @createDate 2024-01-10 20:09:36
* @Entity com.atguigu.spzx.model.entity.product.ProductSku
*/
public interface ProductSkuMapper {

    int saveBatch(List<ProductSku> productSkus);

    List<ProductSku> getProductSkuListByProductId(Long id);

    int deleteByProductId(Long id);

    void update(ProductSku productSku);
}




