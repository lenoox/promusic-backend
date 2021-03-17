package com.lenoox.promusic.common.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Long id){
        super("Resource " + id + " not found" );
    }

}