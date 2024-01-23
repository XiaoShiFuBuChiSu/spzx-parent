package com.atguigu.spzx.service.user.serivce.impl;

import com.atguigu.spzx.common.utils.auth.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.service.user.mapper.UserAddressMapper;
import com.atguigu.spzx.service.user.serivce.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getUserAddressList() {
        Long userInfoId = AuthContextUtil.getUserInfo().getId();
        List<UserAddress> userAddressList = userAddressMapper.selectByUserId(userInfoId);
        return userAddressList;
    }

    @Override
    public UserAddress findUserAddressById(Long userAddressId) {
        UserAddress userAddress = userAddressMapper.selectById(userAddressId);
        return userAddress;
    }
}
