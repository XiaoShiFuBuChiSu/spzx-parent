package com.atguigu.spzx.service.cart.service;

import com.atguigu.spzx.model.entity.cart.CartInfo;

import java.util.List;

public interface CartService {
    List<CartInfo> getCartList();

    void add2Cart(Long skuId, Integer skuNum);

    void removeCartBySkuId(Long skuId);

    void allCheckCart(Integer isChecked);

    void checkChart(String skuId, Integer isChecked);

    void clearChart();

    List<CartInfo> getCheckedCartList();

    CartInfo getCartInfoBySkuId(Long skuId);

    void removeSubmittedCartInfo(List<Long> submittedIds);
}
