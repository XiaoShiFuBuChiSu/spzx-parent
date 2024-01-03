package com.atguigu.spzx.common.service.execption.resolver;

import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.model.vo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description 全局异常处理
 * @Author 王俊然
 * @Date 2024/1/2 14:57
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionResolver {

    /**
     * 全局异常处理错误
     * @param e
     * @return
     */
    @ExceptionHandler(GlobalResultException.class)
    public Result handleGlobal(GlobalResultException e) {
        log.error(e.getMessage());
        return Result.build(null, e.getCode(), e.getMessage());
    }

    /**
     * 自定义校验错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValid(MethodArgumentNotValidException e) {
        String msg = e.getAllErrors().get(0).getDefaultMessage();
        log.error(msg);
        return Result.build(null, 202, msg);
    }

    /**
     * 其他错误
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Result headOthers(RuntimeException e) {
        // log.error(e.getMessage());
        e.printStackTrace();
        return Result.build(null, 500, "未知错误");
    }
}
