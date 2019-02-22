package com.heapix.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JwtTokenProperties {
    @Value("${security.jwt.header}")
    private String header;
    @Value("${security.jwt.prefix}")
    private String prefix;
    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.secret}")
    private String secret;
}
