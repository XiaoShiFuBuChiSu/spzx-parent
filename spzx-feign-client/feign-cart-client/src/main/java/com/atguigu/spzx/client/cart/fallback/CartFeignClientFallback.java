package com.atguigu.spzx.client.cart.fallback;

import com.atguigu.spzx.client.cart.CartFeignClient;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartFeignClientFallback implements CartFeignClient {
    @Override
    public Result<List<CartInfo>> getCheckedCartList() {
        return Result.build(null, ResultCodeEnum.NETWORK_ERROR);
    }

    @Override
    public Result removeSubmitted(List<Long> submittedIds) {
        return Result.build(null, ResultCodeEnum.NETWORK_ERROR);
    }
}
