package com.saood.exception;

import com.saood.enums.ApplicationCode;

public class ApplicationException extends RuntimeException{

   String code;

    public ApplicationException(ApplicationCode applicationCode) {
        super(applicationCode.getStatusMessage());
        this.code = applicationCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
