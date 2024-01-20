package com.atguigu.spzx.model.dto.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

// com.atguigu.spzx.model.dto.product;
@Data
public class ProductDto extends BaseEntity {

    /**
     * 品牌Id
     */
    private Long brandId;
    /**
     * 一级分类
     */
    private Long category1Id;
    /**
     * 二级分类
     */
    private Long category2Id;
    /**
     * 三级分类
     */
    private Long category3Id;

    /**
     * 关键字
     */
    private String keyword;
    /**
     * 排序（综合排序:1 价格升序:2 价格降序:3）
     */
    private Long order;


}