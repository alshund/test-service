package com.heapix.service.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePartnerBo {
    private Long partnerId;
    private Long accountId;
    private InitialsBo initials;
}
