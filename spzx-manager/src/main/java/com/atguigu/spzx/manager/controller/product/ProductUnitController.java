package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.ProductUnitService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductUnitVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "商品单位管理接口")
@RequestMapping("/api/product/productUnit")
public class ProductUnitController {

    @Autowired
    private ProductUnitService productUnitService;

    @GetMapping("/findAll")
    @Operation(summary = "查询所有单位")
    public Result<ProductUnitVo> findAll() {
        List<ProductUnitVo> list = productUnitService.findAll();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/page/{current}/{limit}")
    @Operation(summary = "分页查询商品单位")
    public Result<PageInfo<ProductUnitVo>> page(@PathVariable Integer current,
                                                @PathVariable Integer limit) {
        PageInfo<ProductUnitVo> pageInfo = productUnitService.queryPage(current, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping
    @Operation(summary = "添加商品单位")
    public Result save(@RequestBody ProductUnitVo productUnitVo) {
        boolean res = productUnitService.save(productUnitVo);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "通过Id删除")
    public Result remove(@PathVariable Long id) {
        boolean res = productUnitService.remove(id);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

    @Operation(summary = "更新")
    @PutMapping
    public Result edit(@RequestBody ProductUnitVo productUnitVo) {
        boolean res = productUnitService.edit(productUnitVo);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

}
