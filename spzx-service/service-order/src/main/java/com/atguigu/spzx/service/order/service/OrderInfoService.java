package com.atguigu.spzx.service.order.service;

import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.order.TradeVo;
import com.github.pagehelper.PageInfo;

public interface OrderInfoService {
    TradeVo getTradeInfo();

    TradeVo getSingleTradeInfo(Long skuId);

    Long submitOrder(OrderInfoDto orderInfoDto);

    OrderInfo getOrderInfoById(Long orderId);

    PageInfo<OrderInfo> getOrderInfoList(Long page, Long limit, Integer orderStatus);
}
