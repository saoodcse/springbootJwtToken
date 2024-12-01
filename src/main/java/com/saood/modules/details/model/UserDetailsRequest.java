package com.saood.modules.details.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailsRequest {
    @JsonProperty(value = "userName")
    String userName;
    public void validation() {

        if (this.userName == null || this.userName.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
