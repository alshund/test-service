package com.heapix.service.service;

import com.heapix.service.exception.FileStorageException;
import com.heapix.service.service.bo.CreatePartnerBo;
import com.heapix.service.service.bo.PartnerBo;
import com.heapix.service.service.bo.UpdatePartnerBo;
import org.springframework.web.multipart.MultipartFile;

public interface PartnerService {

    PartnerBo getById(Long id);
    PartnerBo remove(Long loggedId, Long id);
    PartnerBo addPartner(Long loggedId, CreatePartnerBo createBo);
    PartnerBo addImage(Long loggedId, Long id, MultipartFile image) throws FileStorageException;
    PartnerBo updatePartner(Long loggedId, Long id, UpdatePartnerBo updateBo);
}
