package com.atguigu.spzx.service.cart.service.impl;

import com.atguigu.spzx.client.product.ProductFeignClient;
import com.atguigu.spzx.common.service.execption.GlobalResultException;
import com.atguigu.spzx.common.utils.auth.AuthContextUtil;
import com.atguigu.spzx.model.entity.base.BaseEntity;
import com.atguigu.spzx.model.entity.cart.CartInfo;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.service.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public List<CartInfo> getCartList() {
        List<CartInfo> cartInfoList = getAll();

        if (!CollectionUtils.isEmpty(cartInfoList)) {
            return cartInfoList.stream().sorted(Comparator.comparing(BaseEntity::getCreateTime)).toList();
        }

        return new ArrayList<>();
    }

    @Override
    public void add2Cart(Long skuId, Integer skuNum) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();

        CartInfo cartInfo = (CartInfo) redisTemplate.opsForHash().get("user:cart:" + userInfo.getId(), "cart:item:" + skuId);

        if (cartInfo != null) {
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
        } else {
            cartInfo = new CartInfo();
            // 添加购物车商品
            cartInfo.setUserId(userInfo.getId());
            cartInfo.setSkuId(skuId);
            cartInfo.setIsChecked(1);
            cartInfo.setSkuNum(1);
            cartInfo.setCreateTime(new Date());
            // 远程调用
            Result<ProductSku> productResult = productFeignClient.getBySkuId(skuId);
            if (productResult.getCode() != 200) {
                throw new GlobalResultException(productResult.getMessage(), productResult.getCode());
            }

            ProductSku productSku = productResult.getData();
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
        }

        redisTemplate.opsForHash().put("user:cart:" + userInfo.getId(), "cart:item:" + skuId, cartInfo);
    }

    @Override
    public void removeCartBySkuId(Long skuId) {
        Long userInfoId = AuthContextUtil.getUserInfo().getId();
        redisTemplate.opsForHash().delete("user:cart:" + userInfoId, "cart:item:" + skuId);
    }

    @Override
    public void allCheckCart(Integer isChecked) {
        List<CartInfo> cartInfoList = getAll();
        if (!CollectionUtils.isEmpty(cartInfoList)) {
            Map<String, CartInfo> map = new HashMap<>();
            cartInfoList.stream().forEach(cartInfo -> {
                // 修改选择状态
                cartInfo.setIsChecked(isChecked);
                // 添加map
                map.put("cart:item:" + cartInfo.getSkuId(), cartInfo);
            });
            UserInfo userInfo = AuthContextUtil.getUserInfo();
            redisTemplate.opsForHash().putAll("user:cart:" + userInfo.getId(), map);
        }
    }

    @Override
    public void checkChart(String skuId, Integer isChecked) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        Long userInfoId = userInfo.getId();
        HashOperations<String, String, CartInfo> opsHash = redisTemplate.opsForHash();
        CartInfo cartInfo = opsHash.get("user:cart:" + userInfoId, "cart:item:" + skuId);
        if (cartInfo != null) {
            cartInfo.setIsChecked(isChecked);
            opsHash.put("user:cart:" + userInfoId, "cart:item:" + skuId, cartInfo);
        }
    }

    @Override
    public void clearChart() {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        redisTemplate.delete("user:cart:" + userInfo.getId());
    }

    @Override
    public List<CartInfo> getCheckedCartList() {
        List<CartInfo> cartInfoList = getAll();

        List<CartInfo> checkedCartInfoList = cartInfoList.stream().filter(cartInfo -> cartInfo.getIsChecked() == 1).toList();

        return checkedCartInfoList;
    }

    @Override
    public CartInfo getCartInfoBySkuId(Long skuId) {
        Long userInfoId = AuthContextUtil.getUserInfo().getId();
        CartInfo cartInfo = (CartInfo) redisTemplate.opsForHash().get("user:cart:" + userInfoId, "cart:item:" + skuId);
        return cartInfo;
    }

    @Override
    public void removeSubmittedCartInfo(List<Long> submittedIds) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        String[] newSubmittedIds = submittedIds.stream().map(productSkuId -> "cart:item:" + productSkuId).toArray(String[]::new);
        redisTemplate.opsForHash().delete("user:cart:" + userInfo.getId(), newSubmittedIds);
    }

    /**
     * 获取所有购物车信息
     *
     * @return
     */
    private List<CartInfo> getAll() {
        UserInfo userInfo = AuthContextUtil.getUserInfo();

        HashOperations<String, String, CartInfo> opsHash = redisTemplate.opsForHash();

        List<CartInfo> cartInfoList = opsHash.values("user:cart:" + userInfo.getId());

        return cartInfoList;
    }
}
