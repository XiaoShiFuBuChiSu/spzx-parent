package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.manager.service.ValidateCodeService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.UserInfoVo;
import com.atguigu.spzx.model.vo.system.ValidateCodeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:09
 */

@Slf4j
@RestController
@Tag(name = "用户登录相关接口")
@RequestMapping("/system")
public class LoginController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ValidateCodeService validateCodeService;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<LoginVo> login(@RequestBody @Validated LoginDto loginDto) {
        LoginVo loginVo = sysUserService.userLogin(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/userinfo")
    @Operation(summary = "获取Redis中用户信息")
    @Parameters(value = {@Parameter(name = "token", description = "请求中携带的token")})
    public Result<UserInfoVo> getUserInfoOnRedis(@RequestHeader("token") String token) {
        // System.out.println(token);
        UserInfoVo userInfoVo = sysUserService.getUserOnRedis(token);
        return Result.build(userInfoVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/validateCode")
    @Operation(summary = "生成验证码")
    public Result<ValidateCodeVo> getValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/logout")
    @Operation(summary = "用户登出")
    public Result logout(@RequestHeader("token") String token) {
        sysUserService.userLogout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}