package com.atguigu.spzx.model.vo.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户类")
public class UserLoginInfoVo {

   @Schema(description = "昵称")
   private String nickName;

   @Schema(description = "头像")
   private String avatar;

}