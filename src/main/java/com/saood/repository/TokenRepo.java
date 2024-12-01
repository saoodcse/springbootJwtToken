package com.saood.repository;

import com.saood.entities.TokenEntity;
import com.saood.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<TokenEntity, Long> {
    Optional<List<TokenEntity>> findByToken(String token);

    Optional<List<TokenEntity>> findByTokenAndTokenExpire(String token, boolean tokenExpire);

}
