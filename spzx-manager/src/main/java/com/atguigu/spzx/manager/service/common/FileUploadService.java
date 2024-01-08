package com.atguigu.spzx.manager.service.common;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadFile(MultipartFile multipartFile);
}
