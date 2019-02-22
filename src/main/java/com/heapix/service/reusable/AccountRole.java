package com.heapix.service.reusable;

import org.springframework.security.core.GrantedAuthority;

public enum  AccountRole implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
