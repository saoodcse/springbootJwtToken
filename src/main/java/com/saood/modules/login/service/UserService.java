package com.saood.modules.login.service;

import com.saood.entities.SessionEntity;
import com.saood.entities.UserEntity;
import com.saood.enums.ApplicationCode;
import com.saood.enums.ResponseType;
import com.saood.exception.ApplicationException;
import com.saood.jwt.JwtUtils;
import com.saood.modules.login.model.LoginRequest;
import com.saood.modules.login.model.LoginResponse;
import com.saood.repository.SessionRepo;
import com.saood.repository.UserRepo;
import com.saood.response.BaseResponse;
import com.saood.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    final UserRepo userRepo;

    @Autowired
    final SessionRepo sessionRepo;

    @Autowired
    final SessionUtils sessionUtils;

    @Autowired
    final JwtUtils jwtUtils;

    public UserService(UserRepo userRepo, SessionRepo sessionRepo, SessionUtils sessionUtils, JwtUtils jwtUtils) {
        this.userRepo = userRepo;
        this.sessionRepo = sessionRepo;
        this.sessionUtils = sessionUtils;
        this.jwtUtils = jwtUtils;
    }

    public Mono<BaseResponse<LoginResponse>> validateDetails(LoginRequest loginRequest) {
        log.info("Validating login details userName:{}", loginRequest.getUserName());
        loginRequest.validation();
       Optional<UserEntity> userEntity = userRepo.findByUsername(loginRequest.getUserName());

       userEntity.orElseThrow(()-> new ApplicationException(ApplicationCode.USER_NOT_FOUNT));
       String sessionId = sessionUtils.generateSessionId();
        while (sessionRepo.existsBySessionId(sessionId)) {
            sessionId = sessionUtils.generateSessionId();
        }

        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.setSessionId(sessionId);
        sessionEntity.setUser(userEntity.get());

        sessionRepo.save(sessionEntity);

      return Mono.just( new BaseResponse<>(new LoginResponse(
              loginRequest.getUserName(),
              userEntity.get().getRole().getName(), sessionId, jwtUtils.generateToken(sessionId, "foo")
      ), "Successfully logged in", ResponseType.SUCCESS.getCode()));
    }
}
