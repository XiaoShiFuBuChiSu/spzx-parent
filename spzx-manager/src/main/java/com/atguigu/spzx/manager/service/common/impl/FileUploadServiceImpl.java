package com.atguigu.spzx.manager.service.common.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.atguigu.spzx.manager.service.common.FileUploadService;
import com.atguigu.spzx.model.properties.MinioConfigProperty;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j

public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioConfigProperty minioConfigProperty;

    @Override
    public String uploadFile(MultipartFile multipartFile) {
        try {
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minioConfigProperty.getEndPoint())
                            .credentials(minioConfigProperty.getAccessKey(), minioConfigProperty.getSecretKey())
                            .build();

            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfigProperty.getBucketName()).build());
            if (!found) {
                log.info("Bucket '{}' is not found，it will be created.", minioConfigProperty.getBucketName());
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfigProperty.getBucketName()).build());
                log.info("Bucket '{}' was created.", minioConfigProperty.getBucketName());
            } else {
                log.info("Bucket '{}' already exists.", minioConfigProperty.getBucketName());
            }
            // 获取原文件名
            String fileName = multipartFile.getOriginalFilename();
            // 随机新文件名
            String prefix = UUID.randomUUID().toString().replaceAll("-", "");
            // 获取文件后缀
            String suffix = FileUtil.getSuffix(fileName);
            // 获取用户时间用于创建文件夹
            String dateFolder = DateUtil.format(new Date(), "yyyy/MM/dd");
            String uploadName = dateFolder + "/" + prefix + "." + suffix;
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .object(uploadName)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .bucket(minioConfigProperty.getBucketName()).build();

            // 上传到服务器
            minioClient.putObject(objectArgs);
            log.info("uploaded successfully");
            // 返回文件在服务器中的地址
            return minioConfigProperty.getEndPoint() + "/" + minioConfigProperty.getBucketName() + "/" + uploadName;
        } catch (MinioException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
