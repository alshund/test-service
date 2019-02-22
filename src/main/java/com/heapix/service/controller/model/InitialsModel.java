package com.heapix.service.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialsModel {
    private String name;
    private String lastName;
    private String patronymic;
}
