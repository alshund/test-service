package com.heapix.service.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InitialsBo {
    private String name;
    private String lastName;
    private String patronymic;
}
