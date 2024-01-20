package com.atguigu.spzx.model.vo.common;

import lombok.Getter;

@Getter // 提供获取属性值的getter方法
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),

    FAIL(201, "操作失败"),

    // 101 ~ 199 参数错误
    PARAM_EMPTY_ERROR(101, "参数不能为空"),

    // 201 ~ 299 登录、权限错误
    LOGIN_USERNAME_ERROR(202, "用户不存在"),

    LOGIN_PASSWORD_ERROR(203, "用户密码错误"),

    LOGIN_ACCOUNT_DISABLED(204, "用户已被禁用"),

    LOGIN_VERIFY_CODE_ERROR(205, "验证码错误"),

    LOGIN_AUTH_ERROR(206, "用户未登录或登录超时"),

    REGISTRY_CODE_EXPIRED(207, "短信验证码验证码已过期，请重新发送"),
    USERNAME_CONTAINS_ERROR(208, "用户名已存在"),

    // 501 ~ 599 业务错误
    MENU_HAS_CHILDREN(501, "不能删除父级菜单"),
    NETWORK_ERROR(502, "网络错误，请稍候再试"),
    FILE_TYPE_ERROR(510, "文件类型错误");

    private Integer code;      // 业务状态码
    private String message;    // 响应消息

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}