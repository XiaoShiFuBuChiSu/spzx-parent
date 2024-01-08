package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class SysRoleMenu extends BaseEntity {
    private Long roleId;
    private Long menuId;
    private Integer isHalf;
}
