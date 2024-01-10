package com.atguigu.spzx.model.vo.product;

import lombok.Data;

import java.io.Serializable;

@Data
public class BrandVo implements Serializable {
    private Long id;
    private String name;
    private String logo;
}
