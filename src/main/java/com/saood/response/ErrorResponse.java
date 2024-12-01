package com.saood.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty(value = "data")
    private String data;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty("code")
    private String code;

    public ErrorResponse(String data, String status, String code) {
        this.data = data;
        this.status = status;
        this.code = code;
    }

}
