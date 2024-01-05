package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.manager.mapper.SysRoleMapper;
import com.atguigu.spzx.manager.service.SysRoleService;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(readOnly = true)
    public PageInfo<SysRole> getSysRolePage(Integer current, Integer size, SysRoleDto sysRoleDto) {
        PageHelper.startPage(current , size);
        List<SysRole> sysRoleList = sysRoleMapper.selectRolePage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo(sysRoleList);
        return pageInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public SysRole getSysRoleById(Long id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public boolean saveSysRole(SysRole sysRole) {
        int res = sysRoleMapper.insertSysRole(sysRole);
        return res > 0;
    }

    @Override
    public boolean modifySysRole(SysRole sysRole) {
        int res = sysRoleMapper.updateSysRole(sysRole);
        return res > 0;
    }

    @Override
    public boolean removeSysRoleById(Long id) {
        int res = sysRoleMapper.deleteById(id);
        return res > 0;
    }
}
