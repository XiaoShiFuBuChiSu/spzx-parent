package com.atguigu.spzx.common.service.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.common.utils.auth.AuthContextUtil;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.concurrent.TimeUnit;

@Component
public class CloudLoginAuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.contains("auth")) {
            String token = request.getHeader("token");
            if (!StringUtils.hasText(token)) {
                throw new GlobalResultException(ResultCodeEnum.LOGIN_AUTH_ERROR);
            }

            String userInfoStr = redisTemplate.opsForValue().get("user:login:" + token);
            if (!StringUtils.hasText(userInfoStr)) {
                throw new GlobalResultException(ResultCodeEnum.LOGIN_AUTH_ERROR);
            }

            UserInfo userInfo = JSONObject.parseObject(userInfoStr, UserInfo.class);

            AuthContextUtil.setUserInfo(userInfo);
            redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.MINUTES);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AuthContextUtil.removeUserInfo();
    }
}
