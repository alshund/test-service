package com.heapix.service.repository;

import com.heapix.service.repository.entity.PartnerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepo extends CrudRepository<PartnerEntity, Long> { }
