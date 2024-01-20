package com.atguigu.spzx.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description="注册对象")
public class UserRegisterDto {

    @Schema(description = "用户名")
    @NotBlank
    @Pattern(regexp = "/^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$/\n")
    private String username;    // 手机号

    @Schema(description = "密码")
    @NotBlank
    private String password;

    @Schema(description = "昵称")
    @NotBlank
    private String nickName;

    @Schema(description = "手机验证码")
    @NotBlank
    private String code ;

}