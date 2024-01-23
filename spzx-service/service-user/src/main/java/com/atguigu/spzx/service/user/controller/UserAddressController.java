package com.atguigu.spzx.service.user.controller;

import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.user.serivce.UserAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "用户收货地址接口")
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/auth/findUserAddressList")
    public Result<List<UserAddress>> getUserAddressList() {
        List<UserAddress> userAddressList = userAddressService.getUserAddressList();
        return Result.build(userAddressList, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/findUserAddressById/{userAddressId}")
    public Result<UserAddress> findUserAddressById(@PathVariable Long userAddressId){
        UserAddress userAddress = userAddressService.findUserAddressById(userAddressId);
        return Result.build(userAddress, ResultCodeEnum.SUCCESS);
    }
}
