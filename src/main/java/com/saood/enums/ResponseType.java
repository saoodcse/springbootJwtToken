package com.saood.enums;

public enum ResponseType {
    SUCCESS("Success", "6000"),
    WARNING("Warning", "6000"),
    ERROR("Error", "6000");

    private final String status;
    private final String code;

    ResponseType(String status, String code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}
