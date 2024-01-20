package com.atguigu.spzx.model.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员表
 * @TableName user_info
 */
@Data
public class UserInfo implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 备注
     */
    private String memo;

    /**
     * 微信open id
     */
    private String openId;

    /**
     * 微信开放平台unionID
     */
    private String unionId;

    /**
     * 最后一次登录ip
     */
    private String lastLoginIp;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 状态：1为正常，0为禁止
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}