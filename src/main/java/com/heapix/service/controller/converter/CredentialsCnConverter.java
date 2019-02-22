package com.heapix.service.controller.converter;

import com.heapix.service.controller.model.CredentialsModel;
import com.heapix.service.service.bo.CredentialsBo;
import org.mapstruct.Mapper;

@Mapper
public interface CredentialsCnConverter {

    CredentialsModel toCredentialsModel(CredentialsBo credentials);
}
