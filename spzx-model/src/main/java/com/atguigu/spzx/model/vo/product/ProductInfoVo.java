package com.atguigu.spzx.model.vo.product;

import com.atguigu.spzx.model.entity.product.ProductSku;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProductInfoVo implements Serializable {
    private Long id;
    private String name;
    private Long brandId;
    private String brandName;
    private Long category1Id;
    private String category1Name;
    private Long category2Id;
    private String category2Name;
    private Long category3Id;
    private String category3Name;
    private String unitName;
    private String sliderUrls;
    private String specValue;
    private List<ProductSku> productSkuList;
    private String detailsImageUrls;
    private Date createTime;
}
