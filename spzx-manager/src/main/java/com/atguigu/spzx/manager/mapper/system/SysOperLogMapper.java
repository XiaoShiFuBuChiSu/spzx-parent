package com.atguigu.spzx.manager.mapper.system;

import com.atguigu.spzx.model.entity.system.SysOperLog;

/**
 * @author 19556
 * @description 针对表【sys_oper_log(操作日志记录)】的数据库操作Mapper
 * @createDate 2024-01-13 10:55:02
 * @Entity com.atguigu.spzx.model.entity.system.SysOperLog
 */
public interface SysOperLogMapper {

    int insert(SysOperLog sysOperLog);
}




