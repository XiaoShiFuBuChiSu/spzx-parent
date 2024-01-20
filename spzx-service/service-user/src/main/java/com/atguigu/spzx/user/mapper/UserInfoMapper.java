package com.atguigu.spzx.user.mapper;

import com.atguigu.spzx.model.entity.user.UserInfo;

public interface UserInfoMapper {
    int getUsernameCount(String username);

    int insert(UserInfo userInfo);

    UserInfo selectUserInfoByUsername(String username);

    int update(UserInfo newUserInfo);
}
