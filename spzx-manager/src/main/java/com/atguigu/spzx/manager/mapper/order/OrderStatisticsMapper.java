package com.atguigu.spzx.manager.mapper.order;

import com.atguigu.spzx.model.dto.order.OrderStatisticsSearchDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;

import java.util.List;

/**
* @author 19556
* @description 针对表【order_statistics(订单统计)】的数据库操作Mapper
* @createDate 2024-01-12 19:57:37
* @Entity com.atguigu.spzx.model.entity.order.OrderStatistics
*/
public interface OrderStatisticsMapper {

     int insert(OrderStatistics orderStatistics);

     List<OrderStatistics> getAll(OrderStatisticsSearchDto orderStatisticsSearchDto);
}




