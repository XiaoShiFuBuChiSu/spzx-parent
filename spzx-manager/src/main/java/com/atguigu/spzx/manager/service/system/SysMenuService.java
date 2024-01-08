package com.atguigu.spzx.manager.service.system;

import com.atguigu.spzx.model.dto.system.SysRoleMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.MenuTreeVo;

import java.util.List;
import java.util.Map;

public interface SysMenuService {
    List<MenuTreeVo> getMenuTree();

    boolean addMenu(SysMenu sysMenu);

    boolean modifyMenu(SysMenu sysMenu);

    SysMenu getById(Long id);

    boolean removeById(Long id);

    Map<String, Object> getRoleMenuTree(Long roleId);

    void assignRoleMenu(SysRoleMenuDto sysRoleMenuDt);
}
