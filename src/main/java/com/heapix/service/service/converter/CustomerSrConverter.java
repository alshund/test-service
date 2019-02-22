package com.heapix.service.service.converter;

import com.heapix.service.repository.entity.CustomerEntity;
import com.heapix.service.service.bo.AccountBo;
import com.heapix.service.service.bo.CredentialsBo;
import com.heapix.service.service.bo.CustomerBo;
import com.heapix.service.service.bo.InitialsBo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerSrConverter {

    @Mapping(target = "credentials", expression = "java(toCredentialsBo(customer))")
    @Mapping(target = "initials", expression = "java(toInitialsBo(customer))")
    CustomerBo toCustomerBo(CustomerEntity customer);

    @Mapping(target = "login", source = "credentials.login")
    @Mapping(target = "password", source = "credentials.password")
    @Mapping(target = "name", source = "initials.name")
    @Mapping(target = "lastName", source = "initials.lastName")
    @Mapping(target = "patronymic", source = "initials.patronymic")
    CustomerEntity toCustomerEntity(CustomerBo customer);

    InitialsBo toInitialsBo(CustomerEntity customer);

    CredentialsBo toCredentialsBo(CustomerEntity customer);

    @Mapping(target = "credentials", expression = "java(toCredentialsBo(customer))")
    AccountBo toAccountBo(CustomerEntity customer);
}
