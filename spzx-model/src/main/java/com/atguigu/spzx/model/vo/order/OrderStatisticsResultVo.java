package com.atguigu.spzx.model.vo.order;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderStatisticsResultVo implements Serializable {
    private List<String> dateList;
    private List<BigDecimal> amountList;
}
