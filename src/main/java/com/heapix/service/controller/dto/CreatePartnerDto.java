package com.heapix.service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartnerDto {
    private Long partnerId;
    private Long accountId;
    private InitialsDto initials;
}
