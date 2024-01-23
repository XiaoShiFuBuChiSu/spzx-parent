package com.atguigu.spzx.client.product.fallback;

import com.atguigu.spzx.client.product.ProductFeignClient;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Result getBySkuId(Long skuId) {
        // 操作失败，返回网络错误
        return Result.build(null, ResultCodeEnum.NETWORK_ERROR);
    }
}
