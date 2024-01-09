package com.atguigu.spzx.manager.controller.system;

import com.atguigu.spzx.manager.service.system.SysRoleService;
import com.atguigu.spzx.model.dto.system.AssignRoleDto;
import com.atguigu.spzx.model.dto.system.SysRoleDto;
import com.atguigu.spzx.model.entity.system.SysRole;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/system/sysRole")
@Tag(name = "系统权限相关接口")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/page/{current}/{size}")
    @Operation(summary = "分页按条件查询权限列表")
    @Parameters(value = {@Parameter(name = "current", description = "当前页", required = true),
            @Parameter(name = "size", description = "每页记录数", required = true)})
    public Result<PageInfo> page(@PathVariable Integer current,
                                 @PathVariable Integer size,
                                 @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.getSysRolePage(current, size, sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据Id查询角色")
    @Parameters({@Parameter(name = "id", description = "角色id", required = true)})
    public Result<SysRole> get(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getSysRoleById(id);
        return Result.build(sysRole, ResultCodeEnum.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "添加系统角色")
    public Result save(@RequestBody SysRole sysRole) {
        boolean flag = sysRoleService.saveSysRole(sysRole);
        return flag ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @PutMapping
    @Operation(summary = "修改系统角色")
    public Result modify(@RequestBody SysRole sysRole) {
        boolean flag = sysRoleService.modifySysRole(sysRole);
        return flag ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "逻辑删除系统角色")
    @Parameters({@Parameter(name = "id", description = "角色id", required = true)})
    public Result removeById(@PathVariable("id") Long id) {
        boolean flag = sysRoleService.removeSysRoleById(id);
        return flag ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @GetMapping("/getAssignedRole/{userId}")
    @Operation(summary = "根据用户Id获取已分配的权限")
    @Parameters({@Parameter(name = "userId", description = "用户", required = true)})
    public Result<Map> getAssignedRole(@PathVariable Long userId) {
        Map<String, Object> map = sysRoleService.getAssignedRoleInfo(userId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/assignRole")
    @Operation(summary = "为用户分配相应权限")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto) {
        boolean flag = sysRoleService.assignRoleForUser(assignRoleDto);
        return flag? Result.build(null, ResultCodeEnum.SUCCESS): Result.build(null, ResultCodeEnum.FAIL);
    }
}
