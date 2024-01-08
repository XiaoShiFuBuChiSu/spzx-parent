package com.atguigu.spzx.manager.service.system;

import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysRoleService {

    PageInfo<SysRole> getSysRolePage(Integer current, Integer size, SysRoleDto sysRoleDto);

    SysRole getSysRoleById(Long id);

    boolean saveSysRole(SysRole sysRole);

    boolean modifySysRole(SysRole sysRole);

    boolean removeSysRoleById(Long id);

    Map<String, Object> getAssignedRoleInfo(Long userId);

    boolean assignRoleForUser(AssignRoleDto assignRoleDto);
}
