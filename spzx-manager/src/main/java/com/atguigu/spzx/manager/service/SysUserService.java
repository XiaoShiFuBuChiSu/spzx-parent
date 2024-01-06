package com.atguigu.spzx.manager.service;

import com.atguigu.spzx.model.dto.system.LoginDto;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.system.LoginVo;
import com.atguigu.spzx.model.vo.system.UserInfoVo;
import com.github.pagehelper.PageInfo;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:12
 */
public interface SysUserService {
    LoginVo userLogin(LoginDto loginDto);

    UserInfoVo getUserOnRedis(String token);

    void userLogout(String token);

    PageInfo<SysUser> queryPageList(Integer current, Integer limit, SysUserDto sysUserDto);

    boolean addUser(SysUser sysUser);

    boolean modifyUser(SysUser sysUser);

    SysUser getById(Long id);

    boolean removeById(Long id);
}
