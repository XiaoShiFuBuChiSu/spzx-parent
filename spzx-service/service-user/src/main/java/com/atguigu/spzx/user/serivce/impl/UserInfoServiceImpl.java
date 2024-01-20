package com.atguigu.spzx.user.serivce.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.model.dto.user.UserLoginDto;
import com.atguigu.spzx.model.dto.user.UserRegisterDto;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.user.mapper.UserInfoMapper;
import com.atguigu.spzx.user.serivce.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        // 校验验证码
        String redisKey = "user:code:" + userRegisterDto.getUsername();
        String code = redisTemplate.opsForValue().get(redisKey);

        if (!StringUtils.hasText(code)) {
            throw new GlobalResultException(ResultCodeEnum.REGISTRY_CODE_EXPIRED);
        }

        if (!userRegisterDto.getCode().equals(code)) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_VERIFY_CODE_ERROR);
        }

        // 校验成功，删除验证码
        redisTemplate.delete(redisKey);

        // 校验用户名
        if (this.containsUser(userRegisterDto.getUsername())) {
            throw new GlobalResultException(ResultCodeEnum.USERNAME_CONTAINS_ERROR);
        }

        // 校验成功添加数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userRegisterDto.getUsername());
        // 密码通过MD5加密
        userInfo.setPassword(DigestUtils.md5DigestAsHex(userRegisterDto.getPassword().getBytes()));
        userInfo.setPhone(userRegisterDto.getUsername());
        userInfo.setStatus(1);
        userInfo.setIsDeleted(0);
        userInfo.setNickName(userRegisterDto.getNickName());
        userInfo.setAvatar("http://139.198.127.41:9000/sph/20230505/default_handsome.jpg");

        int res = userInfoMapper.insert(userInfo);
    }

    @Override
    public String login(UserLoginDto userLoginDto, HttpServletRequest request) {
        // 通过用户名获取当前用户信息
        UserInfo userInfo = userInfoMapper.selectUserInfoByUsername(userLoginDto.getUsername());
        if (userInfo == null) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_USERNAME_ERROR);
        }

        String md5Pass = DigestUtils.md5DigestAsHex(userLoginDto.getPassword().getBytes());
        if (!md5Pass.equals(userInfo.getPassword())) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        if (userInfo.getStatus() == 0) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_ACCOUNT_DISABLED);
        }

        // 更新用户信息
        UserInfo newUserInfo = new UserInfo();
        newUserInfo.setLastLoginTime(new Date());
        newUserInfo.setLastLoginIp(request.getRemoteAddr());
        newUserInfo.setId(userInfo.getId());
        int res = userInfoMapper.update(newUserInfo);
        // 用户信息存入redis
        userInfo.setLastLoginIp(newUserInfo.getLastLoginIp());
        userInfo.setLastLoginTime(newUserInfo.getLastLoginTime());

        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.MINUTES);

        return token;
    }

    private boolean containsUser(String username) {
        int count = userInfoMapper.getUsernameCount(username);
        return count > 0;
    }
}
