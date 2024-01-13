package com.atguigu.spzx.manager.service.log.impl;

import com.atguigu.spzx.common.log.service.AsyncOptLogService;
import com.atguigu.spzx.manager.mapper.system.SysOperLogMapper;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AsyncOptLogServiceImpl implements AsyncOptLogService {
    @Autowired
    private SysOperLogMapper systemMapper;

    @Async
    public int saveSysOperLog(SysOperLog sysOperLog) {
        return systemMapper.insert(sysOperLog);
    }
}
