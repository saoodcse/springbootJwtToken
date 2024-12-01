package com.saood.response;


public class BaseResponse<T> {
    private T data;
    private String status;
    private String code;

    public BaseResponse(T data, String status, String code) {
        this.data = data;
        this.status = status;
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
