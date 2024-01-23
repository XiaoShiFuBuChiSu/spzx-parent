package com.atguigu.spzx.service.order.controller;

import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.order.TradeVo;
import com.atguigu.spzx.service.order.service.OrderInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orderInfo")
@Tag(name = "订单信息接口")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;

    @GetMapping("/auth/trade")
    @Operation(summary = "购物车下订单")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTradeInfo();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/buy/{skuId}")
    @Operation(summary = "商品详情页购买")
    public Result<TradeVo> buy(@PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.getSingleTradeInfo(skuId);
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/auth/submitOrder")
    @Operation(summary = "提交订单")
    public Result<String> submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/{orderId}")
    @Operation(summary = "根据Id获取订单信息")
    public Result<OrderInfo> getOrderInfo(@PathVariable Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfoById(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/{page}/{limit}")
    @Operation(summary = "分页查询订单信息列表")
    public Result<PageInfo<OrderInfo>> getOrderInfoPage(
            @PathVariable Long page,
            @PathVariable Long limit,
            @RequestParam(required = false) Integer orderStatus) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.getOrderInfoList(page, limit, orderStatus);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

}
