package com.lenoox.promusic.common.dtos;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private HttpStatus status;
    private Object result;

    public ApiResponse(HttpStatus status, Object result){
        this.status = status;
        this.result = result;
    }

    public HttpStatus  getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApiResponse [statusCode=" + status + ", result=" + result +"]";
    }


}

