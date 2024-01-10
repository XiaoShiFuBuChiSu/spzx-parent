package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.BrandService;
import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.BrandVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "商品品牌管理")
@RequestMapping("/api/product/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("/{current}/{limit}")
    @Operation(summary = "获取商品品牌分页")
    public Result<PageInfo<Brand>> getPage(@PathVariable Integer current,
                                           @PathVariable Integer limit) {
        PageInfo<Brand> brands = brandService.getPage(current, limit);
        return Result.build(brands, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    @Operation(summary = "添加商品品牌")
    public Result save(@RequestBody Brand brand) {
        boolean res = brandService.save(brand);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @DeleteMapping("/deleteById/{id}")
    @Operation(summary = "通过Id删除")
    public Result remove(@PathVariable Long id) {
        boolean res = brandService.remove(id);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "获取品牌")
    @GetMapping("/getById/{id}")
    public Result getById(@PathVariable Long id) {
        Brand brand = brandService.getById(id);
        return ObjectUtils.isEmpty(brand) ?
                Result.build(null, ResultCodeEnum.FAIL) :
                Result.build(brand, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "更新")
    @PutMapping("/updateById")
    public Result edit(@RequestBody Brand brand) {
        boolean res = brandService.edit(brand);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "查询所有")
    @GetMapping("/findAll")
    public Result<BrandVo> findAll(@RequestParam(required = false) Map<String, Object> map) {
        List<BrandVo> brands = brandService.findAll(map);
        return Result.build(brands, ResultCodeEnum.SUCCESS);
    }

}
