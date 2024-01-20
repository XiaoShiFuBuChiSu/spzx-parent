package com.atguigu.spzx.model.vo.service.product;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.entity.product.ProductSku;
import lombok.Data;

import java.util.List;

@Data
public class IndexVo {
    private List<Category> categoryList;
    private List<ProductSku> productSkuList;
}
