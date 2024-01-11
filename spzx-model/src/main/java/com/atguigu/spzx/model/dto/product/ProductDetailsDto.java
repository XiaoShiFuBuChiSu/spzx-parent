package com.atguigu.spzx.model.dto.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDetailsDto implements Serializable {
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
}
