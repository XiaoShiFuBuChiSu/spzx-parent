package com.atguigu.spzx.model.entity.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @TableName product_spec
 */
@Data
public class ProductSpec extends BaseEntity {

    private String specName;

    private String specValue;

    private static final long serialVersionUID = 1L;
}