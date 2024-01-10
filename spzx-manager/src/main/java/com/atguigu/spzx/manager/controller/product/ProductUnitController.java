package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.ProductUnitService;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.ProductUnitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
