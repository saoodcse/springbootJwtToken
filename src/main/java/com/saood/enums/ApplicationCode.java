package com.saood.enums;


public enum ApplicationCode {
        USER_NOT_FOUNT("User not found!", "8001"),
        NOT_FOUND("Resource not found", "404"),
        TOKEN_NOT_FOUND("Token not found in store!", "9002"),
        REGISTRATION_FOUND("Registration found!", "70001"),
        TOKEN_ALREADY_USE("Token Already Used!", "70001");



        private final String statusMessage;
        private final String code;

        ApplicationCode(String statusMessage, String code) {
            this.statusMessage = statusMessage;
            this.code = code;
        }

        public String getStatusMessage() {
            return statusMessage;
        }

        public String getCode() {
            return code;
        }
    }

