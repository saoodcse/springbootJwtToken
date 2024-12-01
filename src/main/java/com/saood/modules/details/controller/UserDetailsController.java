package com.saood.modules.details.controller;

import com.saood.modules.details.model.UserDetailsRequest;
import com.saood.modules.details.model.UserDetailsResponse;
import com.saood.modules.details.service.UserDetailsService;
import com.saood.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserDetailsController {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);
    @Autowired
    UserDetailsService userDetailsService;
    @PostMapping("/details")
    ResponseEntity<BaseResponse<UserDetailsResponse>> details(
            @RequestHeader("session-id") String sessionId,
            @RequestBody UserDetailsRequest userDetailsRequest){


        log.info("just reached your request to get details sessionId: {}",sessionId);
        return new ResponseEntity<>(userDetailsService.getDetails(userDetailsRequest), HttpStatus.OK);
    }
}
