package com.atguigu.spzx.model.dto.system;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Schema(description = "用户dto")
@Data
public class SysUserDto {
    private String keyWord;
    private Date beginTime;
    private Date endTime;
}
