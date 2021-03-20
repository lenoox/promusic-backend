package com.lenoox.promusic.common.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ApiResponse {

    private HttpStatus status;
    private Object result;

    public ApiResponse(HttpStatus status, Object result){
        this.status = status;
        this.result = result;
    }

}

