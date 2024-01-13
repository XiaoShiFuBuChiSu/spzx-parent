package com.atguigu.spzx.manager.service.system;

import com.atguigu.spzx.model.dto.system.SysOperLogDto;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import com.github.pagehelper.PageInfo;

public interface SysOperLogService {
    PageInfo<SysOperLog> page(Integer current, Integer limit, SysOperLogDto sysOperLogDto);
}
