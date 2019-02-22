package com.heapix.service.controller.converter;

import com.heapix.service.controller.model.CustomerModel;
import com.heapix.service.controller.model.CustomerSketchModel;
import com.heapix.service.service.bo.CustomerBo;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerCnConverter {

    CustomerModel toCustomerModel(CustomerBo customer);
    CustomerSketchModel toCustomerSketchModel(CustomerBo customer);
}
