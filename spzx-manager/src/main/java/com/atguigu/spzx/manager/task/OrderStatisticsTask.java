package com.atguigu.spzx.manager.task;

import cn.hutool.core.date.DateUtil;
import com.atguigu.spzx.manager.mapper.order.OrderInfoMapper;
import com.atguigu.spzx.manager.mapper.order.OrderStatisticsMapper;
import com.atguigu.spzx.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
@Slf4j
public class OrderStatisticsTask {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;

    /**
     * 每天凌晨2点自动生成前一天的订单统计信息
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void generateOrderStatistics() {
        log.info("开始生成订单统计信息");
        // 获取前一天的时间
        String yesterday = DateUtil.offsetDay(new Date(), -1).toString("yyyy-MM-dd");
        // 查询前一天的订单总额和订单量
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(yesterday);
        if (orderStatistics == null || ObjectUtils.isEmpty(orderStatistics.getOrderDate())) {
            log.info("{} 无订单统计信息", yesterday);
            return;
        }
        // 保存到数据库
        int res = orderStatisticsMapper.insert(orderStatistics);
        log.info("成功生成订单信息");
    }
}
