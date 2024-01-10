package com.atguigu.spzx.manager.service.product.impl;

import cn.hutool.core.io.FileUtil;
import com.alibaba.excel.EasyExcel;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.manager.listener.CategoryExcelListener;
import com.atguigu.spzx.manager.mapper.product.CategoryMapper;
import com.atguigu.spzx.manager.service.product.CategoryService;
import com.atguigu.spzx.model.entity.product.Category;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.product.CategoryExcelDataVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Category> getByParentId(Long parentId) {

        if (parentId == null || parentId <= 0l) {
            parentId = 0l;
        }
        // 根据父id查询所有分类
        List<Category> categories = categoryMapper.selectByParentId(parentId);
        if (CollectionUtils.isEmpty(categories)) {
            return new ArrayList();
        }

        // 查询是否含有子分类
        categories.forEach(category -> {
            // 获取子分类的数量
            int res = categoryMapper.getChildrenCount(category.getId());
            category.setHasChildren(res > 0);
        });
        return categories;
    }

    @Override
    public void exportCategoryExcel(HttpServletResponse response) {
        List<Category> categories = categoryMapper.selectAll();
        if (CollectionUtils.isEmpty(categories)) {
            categories = new ArrayList<>();
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = null;
        try {
            fileName = URLEncoder.encode("分类数据", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), CategoryExcelDataVo.class).sheet("模板").doWrite(categories);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean importCategoryExcel(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = FileUtil.getSuffix(fileName);
        if (!suffix.equals("xls") && !suffix.equals("xlsx")) {
            throw new GlobalResultException(ResultCodeEnum.FILE_TYPE_ERROR);
        }

        try {
            EasyExcel.read(file.getInputStream(),
                            CategoryExcelDataVo.class,
                            new CategoryExcelListener(categoryMapper))
                    .sheet().doRead();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
