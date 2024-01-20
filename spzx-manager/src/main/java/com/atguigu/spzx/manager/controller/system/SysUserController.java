package com.atguigu.spzx.manager.controller.system;

import com.atguigu.spzx.manager.service.system.SysUserService;
import com.atguigu.spzx.model.dto.system.SysUserDto;
import com.atguigu.spzx.model.entity.system.SysUser;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/sysUser")
@Tag(name = "系统用户相关接口")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "分页按条件查询")
    @PostMapping("/page/{current}/{limit}")
    public Result<PageInfo<SysUser>> page(@PathVariable Integer current,
                                          @PathVariable Integer limit,
                                          @RequestBody SysUserDto sysUserDto) {
        PageInfo<SysUser> pageInfo = sysUserService.queryPageList(current, limit, sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加用户")
    @PostMapping
    public Result save(@RequestBody SysUser sysUser) {
        boolean res = sysUserService.addUser(sysUser);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "修改用户")
    @PutMapping
    public Result modify(@RequestBody SysUser sysUser) {
        boolean res = sysUserService.modifyUser(sysUser);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "根据Id查询")
    @GetMapping("/{id}")
    public Result<SysUser> get(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return sysUser != null ? Result.build(sysUser, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "根据id删除")
    @DeleteMapping("/{id}")
    public Result remove (@PathVariable Long id){
        boolean res = sysUserService.removeById(id);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS): Result.build(null, ResultCodeEnum.FAIL);
    }
}
