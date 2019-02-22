package com.heapix.service.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartnerBo {
    private Long partnerId;
    private Long accountId;
    private InitialsBo initials;
}
