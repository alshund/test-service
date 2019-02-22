package com.heapix.service.controller;

import com.heapix.service.controller.converter.PartnerCnConverter;
import com.heapix.service.controller.dto.CreatePartnerDto;
import com.heapix.service.controller.dto.UpdatePartnerDto;
import com.heapix.service.controller.model.AccountPermission;
import com.heapix.service.exception.FileStorageException;
import com.heapix.service.service.PartnerService;
import com.heapix.service.service.bo.CreatePartnerBo;
import com.heapix.service.service.bo.PartnerBo;
import com.heapix.service.service.bo.UpdatePartnerBo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/partners")
public class PartnerController extends BaseController {

    @Autowired
    private PartnerService partnerService;

    private PartnerCnConverter partnerCnv = Mappers.getMapper(PartnerCnConverter.class);

    @GetMapping("/{id}")
    public ResponseEntity getPartner(@PathVariable Long id) {
        PartnerBo partnerBo = partnerService.getById(id);
        AccountPermission permission = getPermission(partnerBo.getCustomer().getId().toString());

        if (permission.isSameUser()) {
            return ok(partnerCnv.toPartnerModel(partnerBo));
        } else {
            return ok(partnerCnv.toPartnerSketchModel(partnerBo));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removePartner(@PathVariable Long id) {
        PartnerBo deleted = partnerService.remove(getLoggedId(), id);
        return ok(partnerCnv.toPartnerModel(deleted));
    }

    @PostMapping
    public ResponseEntity addPartner(@RequestBody CreatePartnerDto createDto) {
        CreatePartnerBo createBo = partnerCnv.toCreatePartnerBo(createDto);

        PartnerBo partnerBo = partnerService.addPartner(getLoggedId(), createBo);
        return ok(partnerCnv.toPartnerModel(partnerBo));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity addImage(@PathVariable Long id, @RequestBody MultipartFile image) throws FileStorageException {
        PartnerBo partnerBo = partnerService.addImage(getLoggedId(), id, image);
        return ok(partnerCnv.toPartnerModel(partnerBo));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePartner(@PathVariable Long id, @RequestBody UpdatePartnerDto updateDto) {
        UpdatePartnerBo updateBo = partnerCnv.toUpdatePartnerBo(updateDto);

        PartnerBo partnerBo = partnerService.updatePartner(getLoggedId(), id, updateBo);
        return ok(partnerCnv.toPartnerModel(partnerBo));
    }
}
