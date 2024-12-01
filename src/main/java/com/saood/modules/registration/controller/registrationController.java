package com.saood.modules.registration.controller;

import com.saood.modules.registration.model.RegistrationRequest;
import com.saood.modules.registration.model.RegistrationResponse;
import com.saood.modules.registration.service.RegistrationService;
import com.saood.response.BaseResponse;
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
@RequestMapping("api/")
public class registrationController {

    private static final Logger log = LoggerFactory.getLogger(registrationController.class);
    @Autowired
    final RegistrationService registrationService;

    public registrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @PostMapping("/registration")
    Mono<ResponseEntity<BaseResponse<RegistrationResponse>>> addRegistration( @RequestBody RegistrationRequest registrationRequest){
         log.info("Just received registration request");
        return registrationService.create(registrationRequest)
                 .map(res -> new ResponseEntity<>(res, HttpStatus.OK));
    }
}
