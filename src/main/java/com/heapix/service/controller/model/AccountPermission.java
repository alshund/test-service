package com.heapix.service.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPermission {
    private long id;
    private boolean isSameUser;
}
