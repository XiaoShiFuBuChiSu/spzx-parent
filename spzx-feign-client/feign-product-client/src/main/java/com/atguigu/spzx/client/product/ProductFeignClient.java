package com.atguigu.spzx.client.product;

import com.atguigu.spzx.client.product.fallback.ProductFeignClientFallback;
import com.atguigu.spzx.model.entity.product.ProductSku;
import com.atguigu.spzx.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product",fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
    @GetMapping("/api/product/getBySkuId/{skuId}")
    Result<ProductSku> getBySkuId(@PathVariable Long skuId);

}
