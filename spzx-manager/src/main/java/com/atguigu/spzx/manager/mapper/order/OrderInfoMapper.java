package com.atguigu.spzx.manager.mapper.order;

import com.atguigu.spzx.model.entity.order.OrderStatistics;

/**
* @author 19556
* @description 针对表【order_info(订单)】的数据库操作Mapper
* @createDate 2024-01-12 19:56:14
* @Entity com.atguigu.spzx.model.entity.order.OrderInfo
*/
public interface OrderInfoMapper {

    OrderStatistics selectOrderStatistics(String yesterday);
}




