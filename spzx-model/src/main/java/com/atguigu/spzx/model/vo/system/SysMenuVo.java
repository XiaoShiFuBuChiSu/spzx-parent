package com.atguigu.spzx.model.vo.system;

import lombok.Data;

import java.util.List;

@Data
public class SysMenuVo {
    private Long id;
    private String name;
    private String title;
    private List<SysMenuVo> children;
}
