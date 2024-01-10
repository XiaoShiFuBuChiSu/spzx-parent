package com.atguigu.spzx.model.entity.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 商品sku
 * @TableName product_sku
 */
@Data
public class ProductSku implements Serializable {
    /**
     * ID
     */
    private Long id;

    /**
     * 商品编号
     */
    private String skuCode;

    /**
     * 
     */
    private String skuName;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 缩略图路径
     */
    private String thumbImg;

    /**
     * 售价
     */
    private BigDecimal salePrice;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

    /**
     * 库存数
     */
    private Integer stockNum;

    /**
     * 销量
     */
    private Integer saleNum;

    /**
     * sku规格信息json
     */
    private String skuSpec;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 体积
     */
    private BigDecimal volume;

    /**
     * 线上状态：0-初始值，1-上架，-1-自主下架
     */
    private Integer status;

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