package com.heapix.service.controller.model;

import com.heapix.service.reusable.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {
    private long id;
    private CredentialsModel credentials;
    private InitialsModel initials;
    private AccountStatus status;
    private double balance;
}
