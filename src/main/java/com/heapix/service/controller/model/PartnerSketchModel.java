package com.heapix.service.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerSketchModel {
    private InitialsModel initials;
    private CustomerSketchModel customer;
}
