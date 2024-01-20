package com.atguigu.spzx.manager.controller.product;

import com.atguigu.spzx.manager.service.product.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product/category")
@Tag(name = "商品分类管理")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "根据父Id获取子分类列表")
    @GetMapping("/findByParentId/{parentId}")
    public Result<List<Category>> findCategoriesByParentId(@PathVariable Long parentId) {
        List<Category> categories = categoryService.getByParentId(parentId);
        return Result.build(categories, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "导出分类excel表格")
    @GetMapping("/exportData")
    public void exportCategoryExcel(HttpServletResponse response) {
        categoryService.exportCategoryExcel(response);
    }

    @Operation(summary = "导入分类excel表格")
    @PostMapping("/importData")
    public Result importCategoryExcel(MultipartFile file) {
        boolean res = categoryService.importCategoryExcel(file);
        return res ? Result.build(null, ResultCodeEnum.SUCCESS) : Result.build(null, ResultCodeEnum.FAIL);
    }

}
