package com.atguigu.spzx.model.vo.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {

    private String token ;
    private String refresh_token ;		// 该字段不会存储对应的值

}