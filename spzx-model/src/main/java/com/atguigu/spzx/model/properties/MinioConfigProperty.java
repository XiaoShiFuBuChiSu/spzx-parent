package com.atguigu.spzx.model.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "spzx.minio")
@Component
public class MinioConfigProperty {
    // minio地址
    private String endPoint;
    // minio 的接口调用凭证，可以用用户名
    private String accessKey;
    // minio 的密钥，可以用用户密码
    private String secretKey;
    // 要使用哪个桶
    private String bucketName;
}
