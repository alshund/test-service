package com.heapix.service.service.bo;

import com.heapix.service.reusable.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBo {
    private Long id;
    private CredentialsBo credentials;
    private InitialsBo initials;
    private AccountStatus status;
    private double balance;
}
