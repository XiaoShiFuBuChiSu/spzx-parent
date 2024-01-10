package com.atguigu.spzx.model.vo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUnitVo implements Serializable {
    private Long id;

    private String name;

    private static final long serialVersionUID = 1L;
}
