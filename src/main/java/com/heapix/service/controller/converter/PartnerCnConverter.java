package com.heapix.service.controller.converter;

import com.heapix.service.controller.dto.CreatePartnerDto;
import com.heapix.service.controller.dto.UpdatePartnerDto;
import com.heapix.service.controller.model.PartnerModel;
import com.heapix.service.controller.model.PartnerSketchModel;
import com.heapix.service.service.bo.CreatePartnerBo;
import com.heapix.service.service.bo.PartnerBo;
import com.heapix.service.service.bo.UpdatePartnerBo;
import org.mapstruct.Mapper;

@Mapper
public interface PartnerCnConverter {

    CreatePartnerBo toCreatePartnerBo(CreatePartnerDto partner);
    UpdatePartnerBo toUpdatePartnerBo(UpdatePartnerDto partner);

    PartnerModel toPartnerModel(PartnerBo partner);
    PartnerSketchModel toPartnerSketchModel(PartnerBo partner);
}
