package com.heapix.service.controller.converter;

import com.heapix.service.controller.model.InitialsModel;
import com.heapix.service.service.bo.InitialsBo;
import org.mapstruct.Mapper;

@Mapper
public interface InitialsCnConverter {

    InitialsModel toInitialsModel(InitialsBo initials);
}
