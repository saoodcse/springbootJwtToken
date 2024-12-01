package com.saood.modules.registration.service;

import com.saood.utils.RegistrationUtils;
import com.saood.entities.RegistrationEntity;
import com.saood.entities.RoleEntity;
import com.saood.entities.UserEntity;
import com.saood.enums.ApplicationCode;
import com.saood.enums.ResponseType;
import com.saood.exception.ApplicationException;
import com.saood.modules.registration.model.RegistrationRequest;
import com.saood.modules.registration.model.RegistrationResponse;
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
public class RegistrationService {

    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);
    @Autowired
    final UserRepo userRepo;

    @Autowired
    final RoleRepo roleRepo;

    @Autowired
    final RegistrationRepo registrationRepo;

    @Autowired
    final RegistrationUtils registrationUtils;

    public RegistrationService(UserRepo userRepo, RoleRepo roleRepo, RegistrationRepo registrationRepo, RegistrationUtils registrationUtils) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.registrationRepo = registrationRepo;
        this.registrationUtils = registrationUtils;
    }

    public Mono<BaseResponse<RegistrationResponse>> create(RegistrationRequest registrationRequest){

        log.info("Validating registration details name: {}", registrationRequest.getFirstName());
        registrationRequest.validate();
        String registrationId = registrationUtils.generateRegistrationId();
        if(registrationRepo.findByUsernameAndDob(registrationRequest.getUsername(), registrationRequest.getDob()).isEmpty()){
            RoleEntity role = new RoleEntity();
            role.setName("USER");
            roleRepo.save(role);

            UserEntity userEntity =  new UserEntity();
            userEntity.setUsername(registrationRequest.getUsername());
            userEntity.setPassword(registrationRequest.getPassword());
            userEntity.setRole(role);

            userRepo.save(userEntity);

            while (registrationRepo.existsByRegistrationId(registrationId)) {
                registrationId = registrationUtils.generateRegistrationId();
            }

            RegistrationEntity registration = new RegistrationEntity(
                    registrationId,
                    registrationRequest.getUsername(),
                    registrationRequest.getFirstName(),
                    registrationRequest.getMiddleName(),
                    registrationRequest.getLastName(),
                    registrationRequest.getMobileNumber(),
                    registrationRequest.getDob(),
                    registrationRequest.getEmail(),
                    registrationRequest.getCountryName(),
                    userEntity
            );

            registrationRepo.save(registration);

        } else {
            throw new ApplicationException(ApplicationCode.REGISTRATION_FOUND);
        }
       return Mono.just(new BaseResponse<>(
               new RegistrationResponse(registrationId),
               "Successfully Created Registration",
               ResponseType.SUCCESS.getCode()));
        }

}
