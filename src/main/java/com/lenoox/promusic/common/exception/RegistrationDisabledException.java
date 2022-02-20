package com.lenoox.promusic.common.exception;

public class RegistrationDisabledException extends RuntimeException {

    public RegistrationDisabledException(){
        super("Registration is disabled");
    }
}
