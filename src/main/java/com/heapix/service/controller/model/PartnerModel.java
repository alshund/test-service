package com.heapix.service.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerModel {
    private Long id;
    private Long partnerId;
    private Long accountId;
    private InitialsModel initials;
    private CustomerModel customer;
    private String photoReference;
}
