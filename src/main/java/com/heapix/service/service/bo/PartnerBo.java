package com.heapix.service.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerBo {
    private Long id;
    private Long partnerId;
    private Long accountId;
    private InitialsBo initials;
    private CustomerBo customer;
    private String photoReference;
}
