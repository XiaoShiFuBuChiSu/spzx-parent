package com.atguigu.spzx.model.entity.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 *
 * @TableName order_info
 */
@Data
public class OrderInfo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 会员_id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 使用的优惠券
     */
    private Long couponId;

    /**
     * 订单总额
     */
    private BigDecimal totalAmount;

    /**
     * 优惠券
     */
    private BigDecimal couponAmount;

    /**
     * 原价金额
     */
    private BigDecimal originalTotalAmount;

    /**
     * 运费
     */
    private BigDecimal feightFee;

    /**
     * 支付方式【1->微信 2->支付宝】
     */
    private Integer payType;

    /**
     * 订单状态【0->待付款；1->待发货；2->已发货；3->待用户收货，已完成；-1->已取消】
     */
    private Integer orderStatus;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 收货人电话
     */
    private String receiverPhone;

    /**
     * 收货人地址标签
     */
    private String receiverTagName;

    /**
     * 省份/直辖市
     */
    private Long receiverProvince;

    /**
     * 城市
     */
    private Long receiverCity;

    /**
     * 区
     */
    private Long receiverDistrict;

    /**
     * 详细地址
     */
    private String receiverAddress;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 确认收货时间
     */
    private Date receiveTime;

    /**
     * 订单备注
     */
    private String remark;

    /**
     * 取消订单时间
     */
    private Date cancelTime;

    /**
     * 取消订单原因
     */
    private String cancelReason;

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

    /**
     * 订单项列表
     */
    List<OrderItem> orderItemList;

    private static final long serialVersionUID = 1L;
}