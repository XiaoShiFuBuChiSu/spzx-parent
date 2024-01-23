package com.atguigu.spzx.model.entity.user;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户地址表
 * @TableName user_address
 */
@Data
public class UserAddress implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 
     */
    private String provinceCode;

    /**
     * 
     */
    private String cityCode;

    /**
     * 
     */
    private String districtCode;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 完整地址
     */
    private String fullAddress;

    /**
     * 是否默认地址（0：否 1：是）
     */
    private Integer isDefault;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识（0：否 1：是）
     */
    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}