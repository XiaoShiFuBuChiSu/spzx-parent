package com.atguigu.spzx.client.cart;

import com.atguigu.spzx.client.cart.fallback.CartFeignClientFallback;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "service-cart", fallback = CartFeignClientFallback.class)
public interface CartFeignClient {

    @GetMapping("/api/order/cart/auth/checkedCartList")
    Result<List<CartInfo>> getCheckedCartList();

    @GetMapping("/api/order/cart/auth/removeSubmitted")
    Result removeSubmitted(@RequestParam List<Long> submittedIds);
}
