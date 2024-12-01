package com.saood.repository;

import com.saood.entities.RegistrationEntity;
import com.saood.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepo extends JpaRepository<RegistrationEntity, Long> {

    Optional<RegistrationEntity> findByUsernameAndDob(String username, String dob) ;

    Optional<RegistrationEntity> findByUserEntity(UserEntity userEntity) ;

    boolean existsByRegistrationId(String registrationId);
}
