package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.ProductSpecService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductSpecVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "商品规格")
@RequestMapping("/api/product/productSpec")
public class ProductSpecController {

    @Autowired
    private ProductSpecService productSpecService;

    @GetMapping("/{current}/{limit}")
    @Operation(summary = "分页查询商品规格")
    public Result<PageInfo<ProductSpecVo>> page(@PathVariable Integer current,
                                                @PathVariable Integer limit) {
        PageInfo<ProductSpecVo> pageInfo = productSpecService.queryPage(current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    @Operation(summary = "添加商品规格")
    public Result save(@RequestBody ProductSpecVo productSpecVo) {
        boolean res = productSpecService.save(productSpecVo);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "通过Id删除")
    public Result remove(@PathVariable Long id) {
        boolean res = productSpecService.remove(id);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "更新")
    @PutMapping("/updateById")
    public Result edit(@RequestBody ProductSpecVo productSpecVo) {
        boolean res = productSpecService.edit(productSpecVo);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "查询所有")
    @GetMapping("/findAll")
    public Result<ProductSpecVo> findAll(){
        List<ProductSpecVo> list = productSpecService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
