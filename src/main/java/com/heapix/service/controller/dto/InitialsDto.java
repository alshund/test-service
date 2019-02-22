package com.heapix.service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialsDto {
    private String name;
    private String lastName;
    private String patronymic;
}
