package com.atguigu.spzx.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.common.service.utils.AuthContextUtil;
import com.atguigu.spzx.manager.mapper.SysUserMapper;
import com.atguigu.spzx.manager.service.SysUserService;
import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.UserInfoVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:12
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper systemUserMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    @Transactional(readOnly = true)
    public LoginVo userLogin(LoginDto loginDto) {
        SysUser systemUser = systemUserMapper.selectUserByUsername(loginDto.getUserName());
        // 校验验证码
        Object code = redisTemplate.opsForValue().get("user:login:validate:" + loginDto.getCodeKey());
        if (code == null || !StringUtils.equalsAnyIgnoreCase(String.valueOf(code), loginDto.getCaptcha())) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_VERIFY_CODE_ERROR);
        }

        // 验证通过删除redis中的验证码
        redisTemplate.delete("user:login:validate:" + loginDto.getCodeKey());

        // 用户名错误
        if (systemUser == null) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_USERNAME_ERROR);
        }

        // 用户被禁用
        if (systemUser.getStatus() == 0) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_ACCOUNT_DISABLED);
        }

        // 密码错误
        String password = loginDto.getPassword();
        // 获取MD5加密后的密码
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!md5Pass.equals(systemUser.getPassword())) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        // 创建Token
        String token = UUID.randomUUID().toString().replace("-", "");
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("user:login:" + token, JSON.toJSONString(systemUser), 30, TimeUnit.MINUTES);

        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        return loginVo;
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoVo getUserOnRedis(String token) {
        SysUser sysUser = AuthContextUtil.get();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(sysUser, userInfoVo);

        return userInfoVo;
    }

    @Override
    public void userLogout(String token) {
        redisTemplate.delete("user:login:" + token);
    }
}
