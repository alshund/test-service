package com.heapix.service.repository;

import com.heapix.service.repository.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<CustomerEntity, Long> { }