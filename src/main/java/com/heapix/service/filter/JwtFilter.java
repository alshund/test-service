package com.heapix.service.filter;

import com.heapix.service.config.JwtTokenProperties;
import com.heapix.service.exception.PermissionDeniedException;
import com.heapix.service.service.CustomerService;
import com.heapix.service.service.TokenProvider;
import com.heapix.service.service.bo.AccountBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtTokenProperties jwtConfig;

    private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(jwtConfig.getHeader());
            if (tokenProvider.isHeaderValid(header)) {
                long id = Long.valueOf(tokenProvider.getToken(header));
                AccountBo accountBo = customerService.getAccount(id);

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(accountBo, null, accountBo.getAuthorities())
                );
            }
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
