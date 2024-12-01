package com.saood.repository;

import com.saood.entities.RoleEntity;
import com.saood.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    Optional<UserEntity> findByName(String username);

}
