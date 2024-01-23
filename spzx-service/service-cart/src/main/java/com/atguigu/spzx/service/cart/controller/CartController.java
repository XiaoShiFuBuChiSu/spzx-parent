package com.atguigu.spzx.service.cart.controller;

import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("auth/addToCart/{skuId}/{skuNum}")
    public Result add2Cart(@PathVariable Long skuId, @PathVariable Integer skuNum) {
        cartService.add2Cart(skuId, skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/cartList")
    public Result getCartList() {
        List<CartInfo> list = cartService.getCartList();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/auth/deleteCart/{skuId}")
    public Result removeCart(@PathVariable Long skuId) {
        cartService.removeCartBySkuId(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/allCheckCart/{isChecked}")
    public Result allCheckCart(@PathVariable Integer isChecked) {
        cartService.allCheckCart(isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/checkCart/{skuId}/{isChecked}")
    public Result checkChart(@PathVariable String skuId, @PathVariable Integer isChecked) {
        cartService.checkChart(skuId, isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/clearCart")
    public Result clearChart() {
        cartService.clearChart();
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/checkedCartList")
    @Operation(summary = "获取购物车中被选中的商品列表")
    public Result<List<CartInfo>> getCheckedCartList() {
        List<CartInfo> list = cartService.getCheckedCartList();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/removeSubmitted")
    @Operation(summary = "删除已经提交的购物车信息")
    public Result removeSubmitted(@RequestParam List<Long> submittedIds) {
        cartService.removeSubmittedCartInfo(submittedIds);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

}
