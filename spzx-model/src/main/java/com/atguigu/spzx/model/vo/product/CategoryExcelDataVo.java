package com.atguigu.spzx.model.vo.product;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryExcelDataVo implements Serializable {
    @ExcelProperty(value = "编号")
    private Long id;
    @ExcelProperty(value = "名称")
    private String name;
    @ExcelProperty(value = "图片地址")
    private String imageUrl;
    @ExcelProperty(value = "父分类编号")
    private Long parentId;
    @ExcelProperty(value = "状态")
    private Integer status;
    @ExcelProperty(value = "排序")
    private Integer orderNum;
}
