package com.heapix.service.service;

import com.heapix.service.config.FileStorageProperties;
import com.heapix.service.exception.EntityNotFoundException;
import com.heapix.service.exception.FileStorageException;
import com.heapix.service.reusable.EntityType;
import com.heapix.service.service.bo.ImageBo;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    private Logger logger = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties properties) throws FileStorageException {
        this.fileStorageLocation = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new FileStorageException(properties.getUploadDir());
        }
    }

    @Override
    public ImageBo downloadFile(String fileName) throws FileStorageException {
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {

            Resource resource = new UrlResource(targetLocation.toUri());
            String base64 = getBase64(resource);
            return new ImageBo(base64);

        } catch (MalformedURLException e) {
            throw new EntityNotFoundException(EntityType.FILE, fileName);
        }
    }

    private String getBase64(Resource resource) throws FileStorageException {
        InputStream is = null;
        try {
            is = resource.getInputStream();
            byte[] bytes = new byte[(int) resource.contentLength()];
            is.read(bytes, 0, (int) resource.contentLength());
            is.close();
            return Base64.encodeBase64String(bytes);
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        } finally {
            closeStream(is);
        }
    }

    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                logger.warn(e.getMessage());
            }
        }
    }

    @Override
    public String uploadFile(String reference, MultipartFile file) throws FileStorageException {
        String fileName = handle(reference, file.getOriginalFilename());
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException(file.getOriginalFilename());
        }
    }

    private String handle(String reference, String originalFileName) {
        String fileName = reference;
        if (fileName == null) {
            int formatIndex = originalFileName.lastIndexOf(".");
            fileName = UUID.randomUUID().toString() + originalFileName.substring(formatIndex);
        }
        return fileName;
    }
}
