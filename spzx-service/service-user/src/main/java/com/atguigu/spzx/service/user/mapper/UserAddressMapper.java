package com.atguigu.spzx.service.user.mapper;

import com.atguigu.spzx.model.entity.user.UserAddress;

import java.util.List;

/**
* @author 19556
* @description 针对表【user_address(用户地址表)】的数据库操作Mapper
* @createDate 2024-01-22 16:33:41
* @Entity com.atguigu.spzx.model.entity.user.UserAddress
*/
public interface UserAddressMapper {

    List<UserAddress> selectByUserId(Long userInfoId);

    UserAddress selectById(Long userAddressId);
}




