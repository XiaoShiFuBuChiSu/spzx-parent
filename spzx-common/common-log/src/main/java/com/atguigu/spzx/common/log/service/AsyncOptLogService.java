package com.atguigu.spzx.common.log.service;

import com.atguigu.spzx.model.entity.system.SysOperLog;

/**
 * 公共的日志业务接口
 */
public interface AsyncOptLogService {
    int saveSysOperLog(SysOperLog sysOperLog);
}
