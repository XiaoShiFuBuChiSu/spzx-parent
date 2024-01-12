package com.atguigu.spzx.manager.controller.order;

import com.atguigu.spzx.manager.service.order.OrderInfoService;
import com.atguigu.spzx.model.dto.order.OrderStatisticsSearchDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.order.OrderStatisticsResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "订单统计")
@RequestMapping("/api/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;


    @GetMapping("/getOrderStatisticsData")
    public Result getOrderStatistics(OrderStatisticsSearchDto orderStatisticsSearchDto) {
        OrderStatisticsResultVo resultVo = orderInfoService.getOrderStatistics(orderStatisticsSearchDto);
        return Result.build(resultVo, ResultCodeEnum.SUCCESS);

    }

}
