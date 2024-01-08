package com.atguigu.spzx.model.dto.system;

import com.atguigu.spzx.model.vo.system.SysRoleMenuVo;
import lombok.Data;

import java.util.List;

@Data
public class SysRoleMenuDto {
    private Long roleId;
    private List<SysRoleMenuVo> menuIdList;
}
