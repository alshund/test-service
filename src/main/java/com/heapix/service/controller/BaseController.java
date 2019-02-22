package com.heapix.service.controller;

import com.heapix.service.controller.model.AccountPermission;
import com.heapix.service.service.bo.AccountBo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {

    protected static String ME = "@me";

    protected ResponseEntity ok(Object object) {
        return ResponseEntity.ok(object);
    }

    protected AccountPermission getPermission(String id) {
        AccountBo loggedAccount = getLoggedAccount();

        if (ME.equals(id) || loggedAccount.getId().toString().equals(id)) {
            return new AccountPermission(loggedAccount.getId(), true);
        } else {
            return new AccountPermission(Long.valueOf(id), false);
        }
    }

    protected AccountBo getLoggedAccount() {
        return (AccountBo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected Long getLoggedId() {
        return getLoggedAccount().getId();
    }
}
