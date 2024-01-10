package com.atguigu.spzx.model.entity.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * 商品单位
 *
 * @TableName product_unit
 */
@Data
public class ProductUnit extends BaseEntity {

    /**
     * 名称
     */
    private String name;


    private static final long serialVersionUID = 1L;
}