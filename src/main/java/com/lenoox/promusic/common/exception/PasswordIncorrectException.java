package com.lenoox.promusic.common.exception;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException(){
        super("Password is incorrect" );
    }
}
