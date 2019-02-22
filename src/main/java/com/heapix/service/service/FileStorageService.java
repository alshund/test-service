package com.heapix.service.service;

import com.heapix.service.exception.FileStorageException;
import com.heapix.service.service.bo.ImageBo;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    ImageBo downloadFile(String fileName) throws FileStorageException;
    String uploadFile(String reference, MultipartFile file) throws FileStorageException;
}
