package com.atguigu.spzx.model.dto.order;

import com.atguigu.spzx.model.entity.order.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "用户订单dto")
public class OrderInfoDto {

    // 送货地址id
    @NotNull(message = "用户地址不能为空")
    @Schema(description = "送货地址id")
    private Long userAddressId;

    // 运费
    @NotNull(message = "运费不能为空")
    @Schema(description = "运费")
    private BigDecimal feightFee;
    // 备注
    @Schema(description = "备注")
    private String remark;
    // 订单明细
    @NotEmpty(message = "订单明细不能为空")
    @Schema(description = "订单明细列表")
    private List<OrderItem> orderItemList;
}