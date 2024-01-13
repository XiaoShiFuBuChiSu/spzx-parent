package com.atguigu.spzx.common.log.annotation;

import com.atguigu.spzx.common.log.enums.BusinessType;
import com.atguigu.spzx.common.log.enums.OperatorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录的注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 类型
     */
    String title();

    /**
     * 业务类型
     *
     * @return
     */
    BusinessType businessType() default BusinessType.Other;

    /**
     * 操作人类型
     */
    OperatorType operatorType() default OperatorType.OTHER;

    /**
     * 是否保存请求数据
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应数据
     */
    boolean isSaveResponseData() default true;
}
