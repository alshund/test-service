package com.heapix.service.service;

import com.heapix.service.exception.EntityNotFoundException;
import com.heapix.service.repository.CustomerRepo;
import com.heapix.service.repository.entity.CustomerEntity;
import com.heapix.service.reusable.EntityType;
import com.heapix.service.service.bo.AccountBo;
import com.heapix.service.service.bo.CustomerBo;
import com.heapix.service.service.bo.InitialsBo;
import com.heapix.service.service.converter.CustomerSrConverter;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    private CustomerSrConverter customerConverter = Mappers.getMapper(CustomerSrConverter.class);

    @Override
    public List<InitialsBo> getAllInitials() {
        List<CustomerEntity> customers = (List<CustomerEntity>) customerRepo.findAll();
        return customers.stream().map(initials ->
                customerConverter.toInitialsBo(initials)).collect(Collectors.toList()
        );
    }

    @Override
    public CustomerBo getById(Long id) {
        CustomerEntity customerEntity = getCustomer(id);
        return customerConverter.toCustomerBo(customerEntity);
    }

    @Override
    public AccountBo getAccount(Long id) {
        CustomerEntity customerEntity = getCustomer(id);
        return customerConverter.toAccountBo(customerEntity);
    }

    private CustomerEntity getCustomer(Long id) {
        return customerRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException(EntityType.CUSTOMER, id)
        );
    }
}