package com.atguigu.spzx.manager.service.system.impl;

import com.atguigu.spzx.manager.mapper.system.SysOperLogMapper;
import com.atguigu.spzx.manager.service.system.SysOperLogService;
import com.atguigu.spzx.model.dto.system.SysOperLogDto;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysOperLogServiceImpl implements SysOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    @Override
    public PageInfo<SysOperLog> page(Integer current, Integer limit, SysOperLogDto sysOperLogDto) {
        PageHelper.startPage(current, limit);
        List<SysOperLog> list = sysOperLogMapper.getAll(sysOperLogDto);
        PageInfo<SysOperLog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
