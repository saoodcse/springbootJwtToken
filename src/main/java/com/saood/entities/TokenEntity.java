package com.saood.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "token")
public class TokenEntity  extends  BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "token_expire", nullable = false)
    private Boolean tokenExpire;

    public TokenEntity(String token, Boolean tokenExpire) {
        this.token = token;
        this.tokenExpire = tokenExpire;
    }

    public TokenEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return token;
    }

    public void setUsername(String token) {
        this.token = token;
    }

    public Boolean getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Boolean tokenExpire) {
        this.tokenExpire = tokenExpire;
    }
}
