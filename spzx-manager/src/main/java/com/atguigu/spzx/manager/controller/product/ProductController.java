package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.dto.product.ProductInfoDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductInfoVo;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/product")
@Tag(name = "产品管理")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{current}/{limit}")
    @Operation(summary = "分页查询产品信息")
    public Result<PageInfo<Product>> page(@PathVariable Integer current,
                                          @PathVariable Integer limit,
                                          ProductDto productDto) {
        PageInfo<Product> pageInfo = productService.queryPage(current, limit, productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/save")
    @Operation(summary = "添加商品信息")
    public Result save(@RequestBody ProductInfoDto productInfoDto) {
        boolean result = productService.save(productInfoDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "根据Id查询")
    public Result getById(@PathVariable Long id) {
        ProductInfoVo productInfoVo = productService.getById(id);
        return Result.build(productInfoVo, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateById")
    @Operation(summary = "根据Id修改")
    public Result editById(@RequestBody ProductInfoDto productInfoDto){
         boolean res = productService.editById(productInfoDto);
         return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据Id删除")
    @DeleteMapping("/deleteById/{id}")
    public Result editById(@PathVariable Long id){
        boolean res = productService.removeById(id);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改审核状态")
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result changeAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus){
        int res = productService.changeAuditStatus(id,auditStatus);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改商品状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result changeStatus(@PathVariable Long id, @PathVariable Integer status){
        int res = productService.changeStatus(id,status);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
