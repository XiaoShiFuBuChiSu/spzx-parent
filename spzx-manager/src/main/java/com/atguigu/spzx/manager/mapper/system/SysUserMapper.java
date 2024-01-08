package com.atguigu.spzx.manager.mapper.system;

import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;

import java.util.List;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:12
 */
public interface SysUserMapper {
    SysUser selectUserByUsername(String username);

    List<SysUser> selectPageList(SysUserDto sysUserDto);

    int insertSysUser(SysUser sysUser);

    int updateSysUser(SysUser sysUser);

    SysUser selectUserById(Long id);

    int deleteById(Long id);
}
