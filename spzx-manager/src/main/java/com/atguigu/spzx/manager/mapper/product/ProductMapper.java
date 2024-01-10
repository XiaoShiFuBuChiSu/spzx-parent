package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.Product;

import java.util.List;

/**
 * @author 19556
 * @description 针对表【product(商品)】的数据库操作Mapper
 * @createDate 2024-01-10 14:56:35
 * @Entity com.atguigu.spzx.model.entity.product.product
 */
public interface ProductMapper {
    List<Product> selectPage(ProductDto productDto);

    int save(Product product);
}
