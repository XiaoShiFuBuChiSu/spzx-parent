package com.atguigu.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户角色Dto")
public class SysRoleDto {
    @Schema(description = "用户角色Id")
    private Long id;
    @Schema(description = "用户角色名称")
    private String roleName;
}
