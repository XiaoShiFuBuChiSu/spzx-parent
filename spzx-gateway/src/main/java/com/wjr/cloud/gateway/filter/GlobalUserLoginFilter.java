package com.wjr.cloud.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.spzx.model.entity.user.UserInfo;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class GlobalUserLoginFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (antPathMatcher.match("/**/auth/**",request.getURI().getPath())) {

            UserInfo userInfo = getUerInfoOnRedis(exchange.getRequest());

            if (userInfo == null) {
                return out(exchange.getResponse(), ResultCodeEnum.LOGIN_AUTH_ERROR);
            }
        }

        return chain.filter(exchange);
    }

    private UserInfo getUerInfoOnRedis(ServerHttpRequest request) {
        List<String> tokenList = request.getHeaders().get("token");
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }

        String token = tokenList.get(0);

        if (!StringUtils.hasText(token)) {
            return null;
        }

        String userInfoStr = redisTemplate.opsForValue().get("user:login:" + token);

        if(!StringUtils.hasText(userInfoStr)){
            return null;
        }

        UserInfo userInfo = JSONObject.parseObject(userInfoStr, UserInfo.class);

        return userInfo;
    }

    private Mono<Void> out(ServerHttpResponse response, ResultCodeEnum resultCodeEnum) {
        Result result = Result.build(null, resultCodeEnum);
        byte[] bits = JSONObject.toJSONString(result).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
