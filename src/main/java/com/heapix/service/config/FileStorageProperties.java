package com.heapix.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class FileStorageProperties {
    @Value("${file.upload-dir}")
    private String uploadDir;
}
