package com.atguigu.spzx.manager.service.impl.common;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.atguigu.spzx.manager.service.common.ValidateCodeService;
import com.atguigu.spzx.model.vo.common.ValidateCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/3 11:17
 */
@Service
@Transactional
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public ValidateCodeVo generateValidateCode() {
        // 生成验证码
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(150, 48, 6, 20);
        // 验证码
        String code = circleCaptcha.getCode();
        // 验证码图片，使用Base64加密
        String imageBase64 = circleCaptcha.getImageBase64();
        // 验证码ID
        String codeKey = UUID.randomUUID().toString().replace("-", "");
        // 封装VO
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(codeKey);
        // 加前缀，让前端知道使用Base64加密
        validateCodeVo.setCodeValue("data:image/png;base64," + imageBase64);
        // code添加到Redis
        redisTemplate.opsForValue().set("user:login:validate:" + codeKey, code, 5, TimeUnit.MINUTES);

        return validateCodeVo;
    }
}
