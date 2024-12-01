package com.saood.repository;

import com.saood.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<SessionEntity, Long> {

    boolean existsBySessionId(String sessionId);

}
