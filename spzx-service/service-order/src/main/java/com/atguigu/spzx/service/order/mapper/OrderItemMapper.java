package com.atguigu.spzx.service.order.mapper;

import com.atguigu.spzx.model.entity.order.OrderItem;

import java.util.List;

/**
* @author 19556
* @description 针对表【order_item(订单项信息)】的数据库操作Mapper
* @createDate 2024-01-22 18:39:31
* @Entity com.atguigu.spzx.model.entity.order.OrderItem
*/
public interface OrderItemMapper {

    int insertBatch(List<OrderItem> orderItemList);

    List<OrderItem> selectByOrderId(Long orderId);
}




