package com.atguigu.spzx.model.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 订单统计
 * @TableName order_statistics
 */
@Data
public class OrderStatistics implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 订单统计日期
     */
    private Date orderDate;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 订单总数
     */
    private Integer totalNum;

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