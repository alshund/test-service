package com.heapix.service.service.converter;

import com.heapix.service.repository.entity.CustomerEntity;
import com.heapix.service.repository.entity.PartnerEntity;
import com.heapix.service.service.bo.CreatePartnerBo;
import com.heapix.service.service.bo.CustomerBo;
import com.heapix.service.service.bo.InitialsBo;
import com.heapix.service.service.bo.PartnerBo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartnerSrConverter {

    @Mapping(target = "initials", expression = "java(toInitialsBo(partner))")
    @Mapping(target = "customer", expression = "java(toCustomerBo(partner))")
    PartnerBo toPartnerBo(PartnerEntity partner);

    @Mapping(target = "name", source = "initials.name")
    @Mapping(target = "lastName", source = "initials.lastName")
    @Mapping(target = "patronymic", source = "initials.patronymic")
    @Mapping(target = "customer", expression = "java(toCustomerEntity(partner))")
    PartnerEntity toPartnerEntity(PartnerBo partner);

    @Mapping(target = "name", source = "initials.name")
    @Mapping(target = "lastName", source = "initials.lastName")
    @Mapping(target = "patronymic", source = "initials.patronymic")
    PartnerEntity toPartnerEntity(CreatePartnerBo partnerBo);

    InitialsBo toInitialsBo(PartnerEntity partner);

    default CustomerBo toCustomerBo(PartnerEntity partner) {
        CustomerSrConverter converter = Mappers.getMapper(CustomerSrConverter.class);
        return converter.toCustomerBo(partner.getCustomer());
    }

    default CustomerEntity toCustomerEntity(PartnerBo partner) {
        CustomerSrConverter converter = Mappers.getMapper(CustomerSrConverter.class);
        return converter.toCustomerEntity(partner.getCustomer());
    }
}
