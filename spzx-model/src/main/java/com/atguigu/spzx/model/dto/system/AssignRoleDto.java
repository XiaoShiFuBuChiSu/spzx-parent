package com.atguigu.spzx.model.dto.system;

import lombok.Data;

import java.util.List;

@Data
public class AssignRoleDto {
    private Long userId;
    private List<Long> roleIds;
}
