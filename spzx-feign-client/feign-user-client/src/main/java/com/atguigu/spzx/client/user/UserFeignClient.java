package com.atguigu.spzx.client.user;

import com.atguigu.spzx.client.user.fallback.UserFeignClientFallback;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/api/user/userAddress/auth/findUserAddressById/{userAddressId}")
    Result<UserAddress> findUserAddressById(@PathVariable Long userAddressId);
}
