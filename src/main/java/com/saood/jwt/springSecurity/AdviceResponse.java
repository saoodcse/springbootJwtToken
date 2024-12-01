package com.saood.jwt.springSecurity;

import com.saood.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class AdviceResponse implements ResponseBodyAdvice<Object> {

    private static final Logger log = LoggerFactory.getLogger(AdviceResponse.class);

    @Autowired
    private final JwtUtils jwtUtils;

    public AdviceResponse(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if (request.getURI().getRawPath().startsWith("/user/details")) {

            // Get session-id header from the request
            String sessionId = request.getHeaders().getFirst("session-id");

            if (sessionId != null && !sessionId.isEmpty()) {
                log.info("session-id found: {}", sessionId);

                String token = jwtUtils.generateToken(sessionId, "foo");

                response.getHeaders().set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
                log.info("Authorization token set.");
            } else {
                log.warn("session-id header is missing or empty.");
            }
        }

        // Return the original body (or modified if needed)
        return body;
    }
}
