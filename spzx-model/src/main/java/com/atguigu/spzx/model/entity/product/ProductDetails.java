package com.atguigu.spzx.model.entity.product;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品sku属性表
 * @TableName product_details
 */
@Data
public class ProductDetails implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 详情图片地址
     */
    private String imageUrls;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}