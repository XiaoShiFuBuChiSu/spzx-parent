package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.CategoryBrandService;
import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryBrandVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "分类品牌管理")
@RequestMapping("/api/product/categoryBrand")
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    @PostMapping("/{current}/{limit}")
    @Operation(summary = "分页按条件查询")
    public Result<PageInfo<CategoryBrandVo>> page(@PathVariable Integer current,
                                                  @PathVariable Integer limit,
                                                  @RequestBody CategoryBrand categoryBrand) {
        PageInfo<CategoryBrandVo> pageInfo = categoryBrandService.queryPage(current, limit, categoryBrand);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    @Operation(summary = "添加")
    public Result save(@RequestBody CategoryBrand categoryBrand) {
        boolean res = categoryBrandService.save(categoryBrand);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @PutMapping("/updateById")
    @Operation(summary = "更新")
    public Result edit(@RequestBody CategoryBrand categoryBrand) {
        boolean res = categoryBrandService.edit(categoryBrand);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "删除")
    public Result remove(@PathVariable Long id) {
        boolean res = categoryBrandService.removeById(id);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }
}
