package com.saood.modules.login.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    @JsonProperty(value = "userName")
    String userName;

    @JsonProperty(value = "password")
    String password;


    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void validation() {

        if (this.userName == null || this.userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (this.password == null || this.password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}
