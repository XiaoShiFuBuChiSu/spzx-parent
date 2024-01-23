package com.atguigu.spzx.client.user.fallback;

import com.atguigu.spzx.client.user.UserFeignClient;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public Result<UserAddress> findUserAddressById(Long userAddressId) {
        return Result.build(null, ResultCodeEnum.NETWORK_ERROR);
    }
}
