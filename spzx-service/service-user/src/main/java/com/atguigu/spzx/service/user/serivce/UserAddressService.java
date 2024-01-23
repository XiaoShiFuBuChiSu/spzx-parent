package com.atguigu.spzx.service.user.serivce;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

public interface UserAddressService {
    List<UserAddress> getUserAddressList();

    UserAddress findUserAddressById(Long userAddressId);
}
