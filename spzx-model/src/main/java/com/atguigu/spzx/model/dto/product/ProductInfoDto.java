package com.atguigu.spzx.model.dto.product;

import com.atguigu.spzx.model.entity.product.ProductSku;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProductInfoDto implements Serializable {
    @NotNull(message = "字段不能为空")
    private Long id;
    @NotEmpty(message = "字段不能为空")
    private String name;
    @NotNull(message = "字段不能为空")
    private Long brandId;
    private String brandName;
    @NotNull(message = "字段不能为空")
    private Long category1Id;
    private String category1Name;
    @NotNull(message = "字段不能为空")
    private Long category2Id;
    private String category2Name;
    @NotNull(message = "字段不能为空")
    private Long category3Id;
    private String category3Name;
    private String unitName;
    private String sliderUrls;
    private String specValue;
    private List<ProductSku> productSkuList;
    private String detailsImageUrls;
    private Date createTime;
}
