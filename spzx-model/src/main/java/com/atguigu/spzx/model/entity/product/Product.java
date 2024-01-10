package com.atguigu.spzx.model.entity.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @TableName product
 */
@Data
public class Product extends BaseEntity {

    private String name;

    private Long brandId;

    private Long category1Id;

    private Long category2Id;

    private Long category3Id;

    private String unitName;

    private String sliderUrls;

    private String specValue;

    private Integer status;

    private Integer auditStatus;

    private String auditMessage;

    // 扩展的属性，用来封装响应的数据
    private String brandName;                // 品牌
    private String category1Name;            // 一级分类
    private String category2Name;            // 二级分类
    private String category3Name;            // 三级分类

    private static final long serialVersionUID = 1L;
}