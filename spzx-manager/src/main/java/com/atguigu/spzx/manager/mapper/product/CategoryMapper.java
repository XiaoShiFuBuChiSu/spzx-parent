package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.product.CategoryExcelDataVo;

import java.util.List;

/**
 * @author 19556
 * @description 针对表【category(商品分类)】的数据库操作Mapper
 * @createDate 2024-01-09 10:24:47
 * @Entity com.atguigu.spzx.model.entity.product.Category
 */
public interface CategoryMapper {

    List<Category> selectByParentId(Long parentId);

    int getChildrenCount(Long id);

    List<Category> selectAll();

    int insertBatch(List<CategoryExcelDataVo> categories);
}




