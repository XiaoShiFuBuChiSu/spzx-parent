package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;

import java.util.List;

/**
* @author 19556
* @description 针对表【sys_role(角色)】的数据库操作Mapper
* @createDate 2024-01-05 10:34:53
* @Entity com.atguigu.spzx.model.entity.system.SysRole
*/
public interface SysRoleMapper {

    List<SysRole> selectRolePage(SysRoleDto sysRoleDto);

    SysRole selectById(Long id);

    int insertSysRole(SysRole sysRole);

    int updateSysRole(SysRole sysRole);

    int deleteById(Long id);
}




