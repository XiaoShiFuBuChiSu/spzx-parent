package com.atguigu.spzx.manager.mapper.product;

import com.atguigu.spzx.model.entity.product.Brand;
import com.atguigu.spzx.model.vo.product.BrandVo;

import java.util.List;

/**
* @author 19556
* @description 针对表【brand(分类品牌)】的数据库操作Mapper
* @createDate 2024-01-09 16:47:28
* @Entity com.atguigu.spzx.model.entity.product.Brand
*/
public interface BrandMapper {

    List<Brand> selectPage();

    int insert(Brand brand);

    int delete(Long id);

    Brand selectById(Long id);

    int update(Brand brand);

    List<BrandVo> selectAll();

    List<BrandVo> selectAllByCategoryId(Object categoryId);
}




