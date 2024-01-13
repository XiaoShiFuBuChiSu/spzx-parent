package com.atguigu.spzx.common.service.interceptor;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.common.utils.auth.AuthContextUtil;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不过滤测试请求OPTIONS
        if (request.getMethod().equals(RequestMethod.OPTIONS.toString())) {
            return true;
        }

        String token = request.getHeader("token");

        // 用户未登录
        if (!StringUtils.hasLength(token)) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_AUTH_ERROR);
        }

        String jsonStr = redisTemplate.opsForValue().get("user:login:" + token);
        // Redis中没找到
        if (!StringUtils.hasLength(jsonStr)) {
            throw new GlobalResultException(ResultCodeEnum.LOGIN_AUTH_ERROR);
        }
        SysUser sysUser = JSON.parseObject(jsonStr, SysUser.class);
        // 用户已经登陆了
        AuthContextUtil.set(sysUser);
        // 重置token过期时间
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(sysUser),30, TimeUnit.MINUTES);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.remove();
    }
}
