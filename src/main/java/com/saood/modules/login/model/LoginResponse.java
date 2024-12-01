package com.saood.modules.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    @JsonProperty(value = "userName")
    String userName;

    @JsonProperty(value = "sessionId")
    String sessionId;
    @JsonProperty(value = "role")
    String role;
    @JsonProperty(value = "token")
    String token;

    public LoginResponse(String userName, String role, String sessionId, String token) {
        this.userName = userName;
        this.role = role;
        this.sessionId = sessionId;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
