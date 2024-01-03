package com.atguigu.spzx.model.dto.system;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/2 10:07
 */
@Data
@Schema(description = "用户登录Dto")
public class LoginDto {
    @NotEmpty(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String userName;
    @NotEmpty(message = "用户密码不能为空")
    @Schema(description = "用户密码")
    private String password;
    @NotEmpty(message = "验证码不能为空")
    @Schema(description = "验证码")
    private String captcha;
    @NotEmpty(message = "验证码不存在")
    @Schema(description = "验证码key")
    private String codeKey;
}
