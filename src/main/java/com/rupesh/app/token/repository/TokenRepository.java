package com.rupesh.app.token.repository;

import com.rupesh.app.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM confirmation_tokens t WHERE t.token = ?1 AND t.user_id = ?2")
    Optional<Token> findByTokenAndUserId(String token, Long userId);

}
