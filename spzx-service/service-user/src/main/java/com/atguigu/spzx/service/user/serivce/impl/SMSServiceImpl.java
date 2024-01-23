package com.atguigu.spzx.service.user.serivce.impl;

import com.atguigu.spzx.service.user.serivce.SMSService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SMSServiceImpl implements SMSService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendMessage(String phoneNum) {
        String code = RandomStringUtils.randomNumeric(4);
        System.out.println(code);
        // 发送短信
        // SMSUtil.sendMessage(phoneNum, code);
        // 将验证码放到Redis上，过期时间5分钟
        redisTemplate.opsForValue().set("user:code:" + phoneNum, code, 5, TimeUnit.MINUTES);
    }
}
