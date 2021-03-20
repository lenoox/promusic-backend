package com.lenoox.promusic.common.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class ErrorResponse {

    private HttpStatus  status;
    private String error;

    public ErrorResponse(HttpStatus status, String error) {
        this.status = status;
        this.error = error;
    }
}

