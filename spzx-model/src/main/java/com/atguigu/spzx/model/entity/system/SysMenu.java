package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

/**
 * @TableName sys_menu
 */
@Data
public class SysMenu extends BaseEntity {

    private Long parentId;

    private String title;

    private String component;

    private Integer sortValue;

    private Integer status;

    private static final long serialVersionUID = 1L;
}