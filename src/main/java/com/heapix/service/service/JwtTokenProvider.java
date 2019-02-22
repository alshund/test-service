package com.heapix.service.service;

import com.heapix.service.config.JwtTokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider implements TokenProvider {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenProperties jwtConfig;

    @Override
    public String getToken(String header) {
        return header.replace(jwtConfig.getPrefix(), "");
    }

    @Override
    public boolean isHeaderValid(String header) {
        return header != null && header.startsWith(jwtConfig.getPrefix());
    }
}
