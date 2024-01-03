package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.UserInfoVo;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:12
 */
public interface SysUserService {
    LoginVo userLogin(LoginDto loginDto);

    UserInfoVo getUserOnRedis(String token);

    void userLogout(String token);
}
