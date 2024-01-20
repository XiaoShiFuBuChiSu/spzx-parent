package com.atguigu.spzx.service.product.controller;

import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.service.product.IndexVo;
import com.atguigu.spzx.service.product.service.IndexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/index")
@RestController
@Tag(name = "前端页面首页接口")
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping
    @Operation(summary = "获取首页数据")
    public Result getIndexData() {
        IndexVo indexVo = indexService.getIndexData();
        return Result.build(indexVo, ResultCodeEnum.SUCCESS);
    }
}
