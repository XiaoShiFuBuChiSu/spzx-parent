package com.atguigu.spzx.manager.service.system.impl;

import com.atguigu.spzx.manager.mapper.system.SysRoleMapper;
import com.atguigu.spzx.manager.service.system.SysRoleService;
import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.system.SysRoleVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(readOnly = true)
    public PageInfo<SysRole> getSysRolePage(Integer current, Integer size, SysRoleDto sysRoleDto) {
        PageHelper.startPage(current, size);
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

    @Override
    public Map<String, Object> getAssignedRoleInfo(Long userId) {
        // 查询所有已分配的角色Id
        List<Long> roleIds = sysRoleMapper.getAssignedRoleIds(userId);
        // 查询所有角色
        List<SysRoleVo> roles = sysRoleMapper.getAllRoles();

        Map<String, Object> map = new HashMap<>();

        if (roleIds == null) {
            map.put("userRoleIds", new ArrayList());
        } else {
            map.put("userRoleIds", roleIds);
        }

        if (roles == null) {
            map.put("allRoles", new ArrayList());
        } else {
            map.put("allRoles", roles);
        }

        return map;
    }

    @Override
    public boolean assignRoleForUser(AssignRoleDto assignRoleDto) {
        int removeResult = sysRoleMapper.deleteAssignedRole(assignRoleDto.getUserId());
        if (!assignRoleDto.getRoleIds().isEmpty()) {
            int addResult = sysRoleMapper.assignRole(assignRoleDto);
        }
        return true;
    }
}
