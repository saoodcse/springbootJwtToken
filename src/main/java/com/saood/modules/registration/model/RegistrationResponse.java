package com.saood.modules.registration.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationResponse {
    @JsonProperty(value = "registrationId")
    private String registrationId;

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public RegistrationResponse(String registrationId) {
        this.registrationId = registrationId;
    }
}
