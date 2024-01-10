package com.atguigu.spzx.manager.service.product;


import com.atguigu.spzx.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    List<Category> getByParentId(Long parentId);

    void exportCategoryExcel(HttpServletResponse response);

    boolean importCategoryExcel(MultipartFile file);
}
