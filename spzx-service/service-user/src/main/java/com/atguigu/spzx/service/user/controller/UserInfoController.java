package com.atguigu.spzx.service.user.controller;

import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.user.UserLoginInfoVo;
import com.atguigu.spzx.service.user.serivce.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userInfo")
@Tag(name = "用户信息接口")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result register(@RequestBody UserRegisterDto userRegisterDto) {
        userInfoService.register(userRegisterDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result register(@RequestBody UserLoginDto userLoginDto, HttpServletRequest request) {

        String token = userInfoService.login(userLoginDto, request);
        return Result.build(token, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/auth/getCurrentUserInfo")
    @Operation(summary = "获取当前登录用户信息")
    public Result getCurrentUserInfo(@RequestHeader("token") String token) {
        UserLoginInfoVo loginInfoVo = userInfoService.getCurrentUserInfo(token);
        return Result.build(loginInfoVo,ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/logout")
    public Result logout(@RequestHeader("token") String token){
        userInfoService.logout(token);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}

