package com.atguigu.spzx.service.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderInfo;

import java.util.List;

public interface OrderInfoMapper {
    int insert(OrderInfo orderInfo);

    OrderInfo selectById(Long orderId);

    List<OrderInfo> selectOrderInfoListWithOrderItems(OrderInfo orderInfo);
}
