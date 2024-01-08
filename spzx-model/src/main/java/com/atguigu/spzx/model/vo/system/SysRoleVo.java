package com.atguigu.spzx.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRoleVo implements Serializable {
    @Schema(description = "角色Id")
    private Long id;

    @Schema(description = "角色名")
    private String roleName;

    @Schema(description = "角色码")
    private String roleCode;
}
