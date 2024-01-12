package com.atguigu.spzx.manager.service.order;

import com.atguigu.spzx.model.dto.order.OrderStatisticsSearchDto;
import com.atguigu.spzx.model.vo.order.OrderStatisticsResultVo;

public interface OrderInfoService {
    OrderStatisticsResultVo getOrderStatistics(OrderStatisticsSearchDto orderStatisticsSearchDto);
}
