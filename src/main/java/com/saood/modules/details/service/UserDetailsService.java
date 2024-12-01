package com.saood.modules.details.service;

import com.saood.entities.RegistrationEntity;
import com.saood.entities.UserEntity;
import com.saood.enums.ApplicationCode;
import com.saood.enums.ResponseType;
import com.saood.exception.ApplicationException;
import com.saood.modules.details.model.UserDetailsRequest;
import com.saood.modules.details.model.UserDetailsResponse;
import com.saood.repository.RegistrationRepo;
import com.saood.repository.RoleRepo;
import com.saood.repository.UserRepo;
import com.saood.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
    @Autowired
    final UserRepo  userRepo;

    @Autowired
    final RegistrationRepo registrationRepo;

    @Autowired
    final RoleRepo roleRepo;

    public UserDetailsService(UserRepo userRepo, RegistrationRepo registrationRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.registrationRepo = registrationRepo;
        this.roleRepo = roleRepo;
    }


    public BaseResponse<UserDetailsResponse> getDetails(UserDetailsRequest userDetailsRequest){

        log.info("Initiating to validate request UserName :{}", userDetailsRequest.getUserName());
        userDetailsRequest.validation();
       UserEntity userEntity = userRepo.findByUsername(userDetailsRequest.getUserName()).orElseThrow(
                ()-> new ApplicationException(ApplicationCode.USER_NOT_FOUNT)
        );
       RegistrationEntity registration =  registrationRepo.findByUserEntity(userEntity).orElseThrow(
                ()-> new ApplicationException(ApplicationCode.USER_NOT_FOUNT)
        );

        UserDetailsResponse userDetailsResponse = getUserDetailsResponse(userEntity, registration);

        return  new BaseResponse<>(userDetailsResponse, ResponseType.SUCCESS.getStatus(), ResponseType.SUCCESS.getCode());
    }

    private UserDetailsResponse getUserDetailsResponse(UserEntity userEntity, RegistrationEntity registration) {

        log.info("Preparing response user: {}", userEntity.getUsername());
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse();

        userDetailsResponse.setUserName(userEntity.getUsername());
        userDetailsResponse.setDob(registration.getDob());
        userDetailsResponse.setFirstName(registration.getFirstName());
        userDetailsResponse.setLastName(registration.getLastName());
        userDetailsResponse.setMiddleName(registration.getMiddleName());
        userDetailsResponse.setEmail(registration.getEmail());
        userDetailsResponse.setCountryName(registration.getCountryName());
        userDetailsResponse.setRole(userEntity.getRole().getName());
        return userDetailsResponse;
    }
}
