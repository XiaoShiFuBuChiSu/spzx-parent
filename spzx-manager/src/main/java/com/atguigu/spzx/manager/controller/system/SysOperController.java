package com.atguigu.spzx.manager.controller.system;

import com.atguigu.spzx.manager.service.system.SysOperLogService;
import com.atguigu.spzx.model.dto.system.SysOperLogDto;
import com.atguigu.spzx.model.entity.system.SysOperLog;
import com.atguigu.spzx.model.vo.common.Result;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/system/sysOperLog")
@Tag(name = "系统操作日志相关接口")
public class SysOperController {
    @Autowired
    private SysOperLogService sysOperLogService;

    @GetMapping("/{current}/{limit}")
    public Result<PageInfo<SysOperLog>> page(@PathVariable Integer current,
                                             @PathVariable Integer limit,
                                             SysOperLogDto sysOperLogDto) {
        PageInfo<SysOperLog> pageInfo = sysOperLogService.page(current, limit, sysOperLogDto);
        return Result.build(pageInfo, 200, "success");
    }
}
