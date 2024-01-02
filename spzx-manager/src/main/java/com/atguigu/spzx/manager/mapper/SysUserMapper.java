package com.atguigu.spzx.manager.mapper;

import com.atguigu.spzx.model.entity.system.SysUser;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:12
 */
public interface SysUserMapper {
    SysUser selectUserByUsername(String username);
}
