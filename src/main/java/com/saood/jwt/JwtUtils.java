package com.saood.jwt;


import com.saood.entities.TokenEntity;
import com.saood.enums.ApplicationCode;
import com.saood.exception.ApplicationException;
import com.saood.repository.TokenRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtils {

    private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
    @Autowired
    final TokenRepo tokenRepo;

    // Replace this with a secure key in a real application, ideally fetched from environment variables
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    public  static final int tokenExpireTime = 10;

    public JwtUtils(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    // Generate token with given user name
    public String generateToken(String userName,String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        String token = createToken(claims, userName);
        tokenRepo.save(new TokenEntity(token, false));
        return token;
    }// Generate token with given user name

    public void markedTodUse(String token) {
        log.info("markedTodUse called");
        List<TokenEntity> tokenEntities = tokenRepo.findByToken(token)
                .orElseThrow(() -> new ApplicationException(ApplicationCode.TOKEN_NOT_FOUND));
        tokenEntities.forEach(tokenEntity -> {
            tokenEntity.setTokenExpire(true);
            tokenRepo.save(tokenEntity);
        });
    }

    public Boolean isUse(String token) {
        Optional<List<TokenEntity>> tokenEntities = tokenRepo.findByToken(token);
        // Check if any tokenEntity has tokenExpire set to true
        log.info("isUsed called {}",tokenEntities.map(entities -> entities.stream()
                .anyMatch(TokenEntity::getTokenExpire)).orElse(false));
        return tokenEntities.map(entities -> entities.stream()
                .anyMatch(TokenEntity::getTokenExpire)).orElse(false);
    }

    // Create a JWT token with specified claims and userName/subject
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .claims(claims)
                .subject(userName)
                .issuedAt(new Date())
                .id(UUID.randomUUID().toString())
                .issuer("saood.com")
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * tokenExpireTime)) // Token valid for 10 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key for JWT token
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String getRoleFromClaims(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get("role", String.class);
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    // Check if the token is expired
    public Boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Explicitly return true if the token is expired
            return true;
        }
    }


    // Validate the token against user details and expiration
    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // Token is expired
            return false;
        } catch (Exception e) {
            // Handle other exceptions (e.g., malformed token)
            return false;
        }
    }

}
