package com.atguigu.spzx.model.vo.product;

import lombok.Data;

@Data
public class CategoryBrandVo {
    private Long id;
    private Long brandId;
    private Long categoryId;
    private String brandName;
    private String categoryName;
    private String logo;
}
