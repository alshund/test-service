package com.heapix.service.service.bo;

import com.heapix.service.reusable.AccountRole;
import com.heapix.service.reusable.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBo implements UserDetails {
    private Long id;
    private CredentialsBo credentials;
    private AccountStatus status;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.asSet(AccountRole.USER);
    }

    @Override
    public String getPassword() {
        return credentials.getPassword();
    }

    @Override
    public String getUsername() {
        return credentials.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
