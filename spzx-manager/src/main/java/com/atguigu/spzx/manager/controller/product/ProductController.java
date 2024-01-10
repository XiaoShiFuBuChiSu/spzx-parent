package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.ProductService;
import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.dto.product.ProductSaveDto;
import com.atguigu.spzx.model.entity.product.Product;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
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
    public Result save(@RequestBody ProductSaveDto productSaveDto) {
        boolean result = productService.save(productSaveDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
