package com.atguigu.spzx.model.entity.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @TableName category_brand
 */
@Data
public class CategoryBrand extends BaseEntity {

    private Long brandId;

    private Long categoryId;

    private static final long serialVersionUID = 1L;
}