package com.atguigu.spzx.model.dto.order;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderStatisticsSearchDto implements Serializable {
    private String createTimeBegin;
    private String createTimeEnd;
}
