package com.atguigu.spzx.model.entity.system;

import com.atguigu.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @TableName sys_role
 */
@Data
@Schema(description = "系统角色实体类")
public class SysRole extends BaseEntity {

    @Schema(description = "角色名")
    @NotEmpty(message = "角色名不能为空")
    private String roleName;

    @Schema(description = "角色码")
    @NotEmpty(message = "角色码不能为空")
    private String roleCode;

    @Schema(description = "描述")
    private String description;

    private static final long serialVersionUID = 1L;
}