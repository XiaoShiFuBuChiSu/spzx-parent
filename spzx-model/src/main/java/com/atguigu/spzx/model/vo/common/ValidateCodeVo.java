package com.atguigu.spzx.model.vo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidateCodeVo implements Serializable {
    private String codeKey;        // 验证码的key
    private String codeValue;      // 图片验证码对应的字符串数据
}