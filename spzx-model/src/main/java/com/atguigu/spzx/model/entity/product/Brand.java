package com.atguigu.spzx.model.entity.product;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @TableName brand
 */
@Data
public class Brand extends BaseEntity {

    private String name;

    private String logo;

    private static final long serialVersionUID = 1L;
}