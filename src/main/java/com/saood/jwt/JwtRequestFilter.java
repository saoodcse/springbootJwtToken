package com.saood.jwt;

import com.saood.enums.ApplicationCode;
import com.saood.exception.ApplicationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    final JwtUtils jwtUtils;

    public JwtRequestFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

            String token = authorizationHeader.substring(7);
            String userName = jwtUtils.extractUsername(token);
            List<GrantedAuthority> authorities = Collections.emptyList();

            if (jwtUtils.validateToken(token)){
                if (jwtUtils.isUse(token)){
                    throw new ApplicationException(ApplicationCode.TOKEN_ALREADY_USE);
                }
                UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(
                        userName, null, authorities
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            jwtUtils.markedTodUse(token);
        }

        filterChain.doFilter(request, response);
    }
}
