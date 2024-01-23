package com.atguigu.spzx.service.product.controller;

import com.atguigu.spzx.model.dto.product.ProductDto;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductItemVo;
import com.atguigu.spzx.service.product.service.ProductService;
import com.atguigu.spzx.service.product.service.ProductSkuService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "商品接口")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSkuService productSkuService;

    @GetMapping("/{page}/{limit}")
    public Result page(@PathVariable Integer page,
                       @PathVariable Integer limit,
                       ProductDto productDto) {
        PageInfo<ProductSku> pageInfo = productService.page(page, limit, productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    ///api/product/item/{skuId}
    @GetMapping("/item/{skuId}")
    public Result getItem(@PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.getProductItem(skuId);
        return Result.build(productItemVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getBySkuId/{skuId}")
    public Result<ProductSku> getBySkuId(@PathVariable Long skuId) {
        ProductSku productSku = productSkuService.getBySkuId(skuId);
        return Result.build(productSku, ResultCodeEnum.SUCCESS);
    }
}
