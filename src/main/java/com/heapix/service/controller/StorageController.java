package com.heapix.service.controller;

import com.heapix.service.exception.FileStorageException;
import com.heapix.service.service.FileStorageService;
import com.heapix.service.service.bo.ImageBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController extends BaseController {

    @Autowired
    private FileStorageService storageService;

    @GetMapping("/{id}")
    public ResponseEntity getBase64(@PathVariable String id) throws FileStorageException {
        ImageBo imageBo = storageService.downloadFile(id);
        return ok(imageBo);
    }

}
