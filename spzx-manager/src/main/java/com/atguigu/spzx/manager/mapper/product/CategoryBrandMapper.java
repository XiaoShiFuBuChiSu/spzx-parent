package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.entity.product.CategoryBrand;
import com.atguigu.spzx.model.vo.product.CategoryBrandVo;

import java.util.List;

/**
* @author 19556
* @description 针对表【category_brand(分类品牌)】的数据库操作Mapper
* @createDate 2024-01-10 09:23:43
* @Entity com.atguigu.spzx.model.entity.product.CategoryBrand
*/
public interface CategoryBrandMapper {

    List<CategoryBrandVo> selectPage(CategoryBrand categoryBrand);

    int insert(CategoryBrand categoryBrand);

    int update(CategoryBrand categoryBrand);

    int deleteById(Long id);
}




