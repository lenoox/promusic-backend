package com.lenoox.promusic.common.exception;

public class DemoActiveException extends RuntimeException {

    public DemoActiveException(){
        super("Demo mode is active");
    }
}
