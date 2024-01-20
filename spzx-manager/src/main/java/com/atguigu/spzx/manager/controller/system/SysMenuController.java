package com.atguigu.spzx.manager.controller.system;

import com.atguigu.spzx.common.log.annotation.Log;
import com.atguigu.spzx.common.log.enums.BusinessType;
import com.atguigu.spzx.common.log.enums.OperatorType;
import com.atguigu.spzx.manager.service.system.SysMenuService;
import com.atguigu.spzx.model.dto.system.SysRoleMenuDto;
import com.atguigu.spzx.model.entity.system.SysMenu;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.system.MenuTreeVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "系统菜单相关接口")
@RequestMapping("/system/sysMenu")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @Operation(summary = "获取菜单树")
    @GetMapping("/menuTree")
    public Result menuTree() {
        List<MenuTreeVo> menuTree = sysMenuService.getMenuTree();
        return Result.build(menuTree, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "添加菜单", businessType = BusinessType.INSERT, operatorType = OperatorType.MANAGER)
    @Operation(summary = "添加菜单")
    @PostMapping
    public Result save(@RequestBody SysMenu sysMenu) {
        boolean res = sysMenuService.addMenu(sysMenu);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "修改菜单")
    @Log(title = "修改菜单", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGER)
    @PutMapping
    public Result modify(@RequestBody SysMenu sysMenu) {
        boolean res = sysMenuService.modifyMenu(sysMenu);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "根据Id查询")
    @GetMapping("/{id}")
    public Result<SysMenu> get(@PathVariable Long id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        return sysMenu != null ? Result.build(sysMenu, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Log(title = "根据id删除菜单", businessType = BusinessType.DELETE, operatorType = OperatorType.MANAGER)
    @Operation(summary = "根据id删除")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        boolean res = sysMenuService.removeById(id);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.MENU_HAS_CHILDREN);
    }

    @Operation(summary = "获取菜单树和角色已分配的菜单")
    @GetMapping("/getRoleMenuTree/{roleId}")
    public Result<Map> getRoleMenuTree(@PathVariable Long roleId) {
        Map<String, Object> map = sysMenuService.getRoleMenuTree(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "分配菜单给角色")
    @PutMapping("/assignRoleMenu")
    @Log(title = "为权限分配菜单", businessType = BusinessType.UPDATE, operatorType = OperatorType.MANAGER)
    public Result assignRoleMenu(@RequestBody SysRoleMenuDto sysRoleMenuDto) {
        sysMenuService.assignRoleMenu(sysRoleMenuDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
