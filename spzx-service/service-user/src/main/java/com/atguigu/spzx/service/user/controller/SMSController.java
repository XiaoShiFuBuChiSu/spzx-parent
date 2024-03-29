package com.atguigu.spzx.service.user.controller;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.service.user.serivce.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    private SMSService smsService;

    @GetMapping("/sendCode/{phoneNum}")
    public Result sendMessage(@PathVariable String phoneNum) {
        smsService.sendMessage(phoneNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
