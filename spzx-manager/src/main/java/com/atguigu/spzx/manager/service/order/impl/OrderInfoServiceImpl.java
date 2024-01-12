package com.atguigu.spzx.manager.service.order.impl;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.order.OrderStatisticsMapper;
import com.atguigu.spzx.manager.service.order.OrderInfoService;
import com.atguigu.spzx.model.dto.order.OrderStatisticsSearchDto;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import com.atguigu.spzx.model.vo.order.OrderStatisticsResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    @Override
    public OrderStatisticsResultVo getOrderStatistics(OrderStatisticsSearchDto orderStatisticsSearchDto) {
        // 查询所有
        List<OrderStatistics> list = orderStatisticsMapper.getAll(orderStatisticsSearchDto);
        // 查询时间的集合
        List<String> dateList = list.stream().map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")).toList();
        // 查询订单总额的集合
        List<BigDecimal> amountList = list.stream().map(OrderStatistics::getTotalAmount).toList();

        OrderStatisticsResultVo orderStatisticsResultVo = new OrderStatisticsResultVo();
        orderStatisticsResultVo.setAmountList(amountList);
        orderStatisticsResultVo.setDateList(dateList);

        return orderStatisticsResultVo;
    }
}
