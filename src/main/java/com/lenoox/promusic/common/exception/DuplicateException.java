package com.lenoox.promusic.common.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String value){
        super("Duplicate " + value);
    }
}