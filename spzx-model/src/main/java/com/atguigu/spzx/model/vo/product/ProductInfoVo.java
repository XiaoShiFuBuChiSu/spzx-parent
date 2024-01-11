package com.atguigu.spzx.model.vo.product;

import com.atguigu.spzx.model.entity.product.ProductSku;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductInfoVo implements Serializable {
    private Long id;
    private String name;
    private Long brandId;
    private Long category1Id;
    private Long category2Id;
    private Long category3Id;
    private String unitName;
    private String sliderUrls;
    private String specValue;
    private List<ProductSku> productSkuList;
    private String detailsImageUrls;
}
