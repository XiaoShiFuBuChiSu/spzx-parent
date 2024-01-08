package com.atguigu.spzx.model.vo.system;

import lombok.Data;

import java.util.List;

@Data
public class MenuTreeVo {
    private Long id;
    private Long parentId;
    private String title;
    private String component;
    private Integer sortValue;
    private Integer status;

    // 下级列表
    private List<MenuTreeVo> children;
}
