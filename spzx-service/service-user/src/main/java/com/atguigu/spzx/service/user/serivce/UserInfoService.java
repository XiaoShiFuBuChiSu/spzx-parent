package com.atguigu.spzx.service.user.serivce;

import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.vo.user.UserLoginInfoVo;
import jakarta.servlet.http.HttpServletRequest;

public interface UserInfoService {
    void register(UserRegisterDto userRegisterDto);

    String login(UserLoginDto userLoginDto, HttpServletRequest request);

    UserLoginInfoVo getCurrentUserInfo(String token);

    void logout(String token);
}
