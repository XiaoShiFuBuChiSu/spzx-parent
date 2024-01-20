package com.atguigu.spzx.user.serivce;

import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto, HttpServletRequest request);
}
