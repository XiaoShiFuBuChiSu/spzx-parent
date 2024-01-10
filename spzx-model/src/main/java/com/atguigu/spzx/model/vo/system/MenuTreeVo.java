package com.atguigu.spzx.model.vo.system;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MenuTreeVo {
    private Long id;
    private Long parentId;

    private String title;

    private String component;

    private Integer sortValue;

    private Integer status;

    private Date createTime;

    private List<MenuTreeVo> children;

    private static final long serialVersionUID = 1L;
}
