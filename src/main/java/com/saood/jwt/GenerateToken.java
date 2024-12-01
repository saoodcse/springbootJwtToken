package com.saood.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class GenerateToken {
    private static final Logger log = LoggerFactory.getLogger(GenerateToken.class);
    @Autowired JwtUtils jwtUtils;


    @GetMapping("token")
    String token(@RequestHeader("userName") String userName, @RequestHeader("role") String role){
        String token = jwtUtils.generateToken(userName, role);
        log.info("User: {}", jwtUtils.extractUsername(token));
        log.info("role: {}", jwtUtils.getRoleFromClaims(token));
        log.info("validation: {}", jwtUtils.validateToken(token));
        log.info("expiration: {}", jwtUtils.extractExpiration(token));
        log.info("isTokenExpired: {}", jwtUtils.isTokenExpired(token));

        return token;
    }

}
