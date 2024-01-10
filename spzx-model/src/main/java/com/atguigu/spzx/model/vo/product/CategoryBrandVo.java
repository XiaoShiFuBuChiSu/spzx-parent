package com.atguigu.spzx.model.vo.product;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryBrandVo {
    private Long id;
    private Long brandId;
    private Long categoryId;
    private String brandName;
    private String categoryName;
    private String logo;
    private Date createTime;
}
