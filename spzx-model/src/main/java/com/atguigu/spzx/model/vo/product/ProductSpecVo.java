package com.atguigu.spzx.model.vo.product;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductSpecVo implements Serializable {
    private Long id;
    private String specName;
    private String specValue;
    private Date createTime;
    private static final long serialVersionUID = 1L;
}
