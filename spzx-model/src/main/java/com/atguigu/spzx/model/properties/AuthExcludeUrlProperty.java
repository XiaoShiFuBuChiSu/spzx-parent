package com.atguigu.spzx.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "spzx.intercept.exclude")
@Component
public class AuthExcludeUrlProperty {
    private List<String> urls;
}
