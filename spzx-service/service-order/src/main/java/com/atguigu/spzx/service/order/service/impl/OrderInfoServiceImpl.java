package com.atguigu.spzx.service.order.service.impl;

import com.atguigu.spzx.client.cart.CartFeignClient;
import com.atguigu.spzx.client.product.ProductFeignClient;
import com.atguigu.spzx.client.user.UserFeignClient;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.common.utils.auth.AuthContextUtil;
import com.atguigu.spzx.model.dto.order.OrderInfoDto;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.entity.order.OrderInfo;
import com.atguigu.spzx.model.entity.order.OrderItem;
import com.atguigu.spzx.model.entity.order.OrderLog;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.UserAddress;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import com.atguigu.spzx.model.vo.order.TradeVo;
import com.atguigu.spzx.service.order.mapper.OrderInfoMapper;
import com.atguigu.spzx.service.order.mapper.OrderItemMapper;
import com.atguigu.spzx.service.order.mapper.OrderLogMapper;
import com.atguigu.spzx.service.order.service.OrderInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {
    /* mapper */
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    /* 远程调用 */
    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public TradeVo getTradeInfo() {
        // 1. 远程调用Cart获取购物车中选中的商品信息
        Result<List<CartInfo>> result = cartFeignClient.getCheckedCartList();
        if (result.getCode() != 200) {
            throw new GlobalResultException(result.getMessage(), result.getCode());
        }
        List<CartInfo> cartInfoList = result.getData();
        List<OrderItem> orderItemList = new ArrayList<>();

        // 2. 构建集合同时计算商品总价值
        BigDecimal totalAmount = new BigDecimal(0);
        if (!CollectionUtils.isEmpty(cartInfoList)) {
            for (CartInfo cartInfo : cartInfoList) {
                Map<String, Object> map = cartInfo2OrderItem(cartInfo);
                // 获取商品价格
                BigDecimal currentAmount = (BigDecimal) map.get("currentAmount");
                totalAmount = totalAmount.add(currentAmount);

                // 添加到集合中
                OrderItem orderItem = (OrderItem) map.get("orderItem");
                orderItemList.add(orderItem);
            }
        }

        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);

        return tradeVo;
    }

    @Override
    public TradeVo getSingleTradeInfo(Long skuId) {

        Result<ProductSku> result = productFeignClient.getBySkuId(skuId);

        if (result.getCode() != 200) {
            throw new GlobalResultException(result.getMessage(), result.getCode());
        }
        if (result.getData() != null) {
            // productSku转orderItem
            ProductSku productSku = result.getData();
            OrderItem orderItem = new OrderItem();

            orderItem.setSkuNum(1);
            orderItem.setSkuId(productSku.getId());
            orderItem.setSkuPrice(productSku.getSalePrice());
            orderItem.setThumbImg(productSku.getThumbImg());
            orderItem.setSkuName(productSku.getSkuName());

            TradeVo tradeVo = new TradeVo();
            tradeVo.setOrderItemList(Collections.singletonList(orderItem));
            tradeVo.setTotalAmount(orderItem.getSkuPrice());
            return tradeVo;
        }
        return new TradeVo();
    }

    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        // 商品库存检查，如果库存不够则抛出异常
        checkProductSkuNum(orderItemList);

        // 1. 订单信息添加到orderInfo
        OrderInfo orderInfo = saveOrderInfo(orderInfoDto, userInfo);
        // 2. 订单项信息添加到orderItem
        List<Long> orderItemIds = saveOrderItem(orderItemList, orderInfo.getId());
        // 3. 订单日志信息添加到orderLog
        saveOrderLog(orderInfo);
        // 4. 远程调用购物车删除购物车中已提交订单的商品(选中的)
        Result result = cartFeignClient.removeSubmitted(orderItemIds);
        if (result.getCode() != 200) {
            throw new GlobalResultException(result.getMessage(), result.getCode());
        }
        return orderInfo.getId();
    }

    @Override
    public OrderInfo getOrderInfoById(Long orderId) {
        OrderInfo orderInfo = orderInfoMapper.selectById(orderId);
        return orderInfo;
    }

    @Override
    public PageInfo<OrderInfo> getOrderInfoList(Long page, Long limit, Integer orderStatus) {
        PageHelper.startPage(page.intValue(), limit.intValue());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderStatus(orderStatus);
        orderInfo.setUserId(AuthContextUtil.getUserInfo().getId());
        List<OrderInfo> orderInfoList = orderInfoMapper.selectOrderInfoListWithOrderItems(orderInfo);
        PageInfo<OrderInfo> pageInfo = new PageInfo<>(orderInfoList);
        return pageInfo;
    }

    private void checkProductSkuNum(List<OrderItem> orderItemList) {
        for (OrderItem orderItem : orderItemList) {
            Result<ProductSku> result = productFeignClient.getBySkuId(orderItem.getSkuId());
            if (result.getCode() != 200) {
                throw new GlobalResultException(result.getMessage(), result.getCode());
            }
            ProductSku productSku = result.getData();
            if (productSku == null) {
                throw new GlobalResultException(ResultCodeEnum.PRODUCT_NOT_CONTAINS);
            }

            if (productSku.getStockNum() < orderItem.getSkuNum()) {
                throw new GlobalResultException("商品[" + productSku.getSkuName() + "库存不足，请选择其他规格]", 504);
            }
        }
    }

    private void saveOrderLog(OrderInfo orderInfo) {
        OrderLog orderLog = new OrderLog();
        orderLog.setNote("提交订单");
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);

        int res = orderLogMapper.save(orderLog);
    }

    private List<Long> saveOrderItem(List<OrderItem> orderItemList, Long orderInfoId) {
        List<Long> orderItemIdList = new ArrayList<>();
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfoId);
            orderItemIdList.add(orderItem.getSkuId());
        }
        int res = orderItemMapper.insertBatch(orderItemList);
        return orderItemIdList;
    }

    /**
     * 保存用户信息
     *
     * @param orderInfoDto
     * @return 返回订单id
     */
    private OrderInfo saveOrderInfo(OrderInfoDto orderInfoDto, UserInfo userInfo) {
        OrderInfo orderInfo = new OrderInfo();
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        // 设置订单编号：uuid + userId
        String orderNo = UUID.randomUUID().toString().replace("-", "").toString() + userInfo.getId();
        orderInfo.setUserId(userInfo.getId());
        orderInfo.setNickName(userInfo.getNickName());
        orderInfo.setOrderNo(orderNo);
        // 计算订单总额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setOrderStatus(0);
        orderInfo.setRemark(orderInfoDto.getRemark());
        orderInfo.setCouponAmount(new BigDecimal(0));

        // 获取用户地址信息
        Result<UserAddress> result = userFeignClient.findUserAddressById(orderInfoDto.getUserAddressId());
        if (result.getCode() != 200) {
            throw new GlobalResultException(result.getMessage(), result.getCode());
        }

        UserAddress userAddress = result.getData();
        if (userAddress == null) {
            throw new GlobalResultException(ResultCodeEnum.USER_ADDRESS_ERROR);
        }

        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        // 添加到表中
        int res = orderInfoMapper.insert(orderInfo);

        return orderInfo;
    }


    /**
     * 商品信息转换成订单信息并返回订单总额
     *
     * @param cartInfo
     * @return
     */
    private Map<String, Object> cartInfo2OrderItem(CartInfo cartInfo) {
        OrderItem orderItem = new OrderItem();

        orderItem.setSkuName(cartInfo.getSkuName());
        // 商品数量
        Integer skuNum = cartInfo.getSkuNum();
        orderItem.setSkuNum(skuNum);
        // 商品单价
        BigDecimal cartPrice = cartInfo.getCartPrice();
        orderItem.setSkuPrice(cartPrice);
        orderItem.setThumbImg(cartInfo.getImgUrl());
        orderItem.setSkuId(cartInfo.getSkuId());

        // 计算价格
        BigDecimal currentAmount = cartPrice.multiply(new BigDecimal(skuNum));

        Map<String, Object> map = new HashMap<>();
        map.put("orderItem", orderItem);
        map.put("currentAmount", currentAmount);

        return map;
    }
}
