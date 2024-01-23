package com.atguigu.spzx.model.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单项信息
 * @TableName order_item
 */
@Data
public class OrderItem implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * order_id
     */
    private Long orderId;

    /**
     * 商品sku编号
     */
    private Long skuId;

    /**
     * 商品sku名字
     */
    private String skuName;

    /**
     * 商品sku图片
     */
    private String thumbImg;

    /**
     * 商品sku价格
     */
    private BigDecimal skuPrice;

    /**
     * 商品购买的数量
     */
    private Integer skuNum;

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