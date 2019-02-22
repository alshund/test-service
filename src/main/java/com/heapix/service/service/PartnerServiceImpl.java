package com.heapix.service.service;

import com.heapix.service.exception.EntityNotFoundException;
import com.heapix.service.exception.FileStorageException;
import com.heapix.service.exception.PermissionDeniedException;
import com.heapix.service.repository.PartnerRepo;
import com.heapix.service.repository.entity.CustomerEntity;
import com.heapix.service.repository.entity.PartnerEntity;
import com.heapix.service.reusable.EntityType;
import com.heapix.service.service.bo.CreatePartnerBo;
import com.heapix.service.service.bo.PartnerBo;
import com.heapix.service.service.bo.UpdatePartnerBo;
import com.heapix.service.service.converter.PartnerSrConverter;
import org.hibernate.Session;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PartnerServiceImpl implements PartnerService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PartnerRepo partnerRepo;

    @Autowired
    private FileStorageService storageService;

    private PartnerSrConverter partnerConverter = Mappers.getMapper(PartnerSrConverter.class);

    @Override
    public PartnerBo getById(Long id) {
        PartnerEntity partnerEntity = getPartner(id);
        return partnerConverter.toPartnerBo(partnerEntity);
    }

    @Override
    public PartnerBo remove(Long loggedId, Long id) {
        PartnerEntity partner = getPartner(id);

        if (partner.getCustomer().getId().equals(id)) {
            partnerRepo.deleteById(id);
            return partnerConverter.toPartnerBo(partner);
        } else {
            throw new PermissionDeniedException();
        }
    }

    @Override
    @Transactional
    public PartnerBo addPartner(Long loggedId, CreatePartnerBo createBo) {

        Session session = entityManager.unwrap(Session.class);
        CustomerEntity customer = session.load(CustomerEntity.class, loggedId);

        PartnerEntity partnerEntity = partnerConverter.toPartnerEntity(createBo);
        partnerEntity.setCustomer(customer);

        PartnerEntity created = partnerRepo.save(partnerEntity);
        return partnerConverter.toPartnerBo(created);
    }

    @Override
    public PartnerBo addImage(Long loggedId, Long id, MultipartFile image) throws FileStorageException {
        PartnerEntity partner = getPartner(id);

        if (partner.getCustomer().getId().equals(loggedId)) {
            PartnerEntity updated = addImage(partner, image);
            partnerRepo.save(updated);
            return partnerConverter.toPartnerBo(updated);
        } else {
            throw new PermissionDeniedException();
        }
    }

    private PartnerEntity addImage(PartnerEntity partner, MultipartFile image) throws FileStorageException {
        String reference = storageService.uploadFile(partner.getPhotoReference(), image);
        if (!reference.equals(partner.getPhotoReference())) {
            partner.setPhotoReference(reference);
        }
        return partner;
    }

    @Override
    public PartnerBo updatePartner(Long loggedId, Long id, UpdatePartnerBo updateBo) {
        PartnerEntity partner = getPartner(id);

        if (partner.getCustomer().getId().equals(loggedId)) {

            partner.setPartnerId(updateBo.getPartnerId());
            partner.setAccountId(updateBo.getAccountId());

            partner.setName(updateBo.getInitials().getName());
            partner.setLastName(updateBo.getInitials().getLastName());
            partner.setPatronymic(updateBo.getInitials().getPatronymic());

            PartnerEntity updated = partnerRepo.save(partner);
            return partnerConverter.toPartnerBo(updated);
        } else {
            throw new PermissionDeniedException();
        }
    }

    private PartnerEntity getPartner(Long id) {
        return partnerRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(EntityType.PARTNER, id)
        );
    }
}
