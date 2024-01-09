package com.atguigu.spzx.manager.mapper.system;

import com.atguigu.spzx.model.dto.system.SysRoleMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.system.MenuTreeVo;

import java.util.List;

/**
* @author 19556
* @description 针对表【sys_menu(菜单表)】的数据库操作Mapper
* @createDate 2024-01-08 11:30:57
* @Entity com.atguigu.spzx.model.entity.system.SysMenu
*/
public interface SysMenuMapper {

    List<MenuTreeVo> selectAllMenus();

    int insertSysMenu(SysMenu sysMenu);

    int updateSysMenu(SysMenu sysMenu);

    SysMenu selectMenuById(Long id);

    int deleteById(Long id);

    List<Long> getMenusByRoleId(Long roleId);

    int deleteMenusByRoleId(Long roleId);

    int insertSysRoleMenu(SysRoleMenuDto sysRoleMenuDto);

    int getChildrenNum(Long id);

    List<MenuTreeVo> selectMenusByUserId(Long id);
}




