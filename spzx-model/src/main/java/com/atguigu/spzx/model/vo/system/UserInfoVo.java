package com.atguigu.spzx.model.vo.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author 王俊然
 * @Date 2024/1/3 9:30
 */
@Data
public class UserInfoVo implements Serializable {
    private String id;
    private String name;
    private String avatar;
    private String role;
}
