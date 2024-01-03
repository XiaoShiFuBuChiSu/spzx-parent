package com.atguigu.spzx.common.service.execption;

import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @Description 全局异常封装统一返回结果
 * @Author 王俊然
 * @Date 2024/1/2 15:16
 */
@Data
public class GlobalResultException extends RuntimeException {
    private Integer code;

    public GlobalResultException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public GlobalResultException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
