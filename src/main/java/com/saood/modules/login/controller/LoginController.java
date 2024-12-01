package com.saood.modules.login.controller;

import com.saood.modules.login.model.LoginRequest;
import com.saood.modules.login.model.LoginResponse;
import com.saood.modules.login.service.UserService;
import com.saood.response.BaseResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @PostMapping("/login")
    Mono<ResponseEntity<BaseResponse<LoginResponse>>> validateLogin(@RequestBody @Valid LoginRequest loginRequest){
       log.info("Just reached your request to login in application");
       return userService.validateDetails(loginRequest)
               .map(res -> new ResponseEntity<>(res, HttpStatus.OK));
    }
}
