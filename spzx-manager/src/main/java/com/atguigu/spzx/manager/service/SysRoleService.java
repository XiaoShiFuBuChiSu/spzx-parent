package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

public interface SysRoleService {

    PageInfo<SysRole> getSysRolePage(Integer current, Integer size, SysRoleDto sysRoleDto);

    SysRole getSysRoleById(Long id);

    boolean saveSysRole(SysRole sysRole);

    boolean modifySysRole(SysRole sysRole);

    boolean removeSysRoleById(Long id);
}
