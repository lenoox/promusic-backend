package com.lenoox.promusic.common.exception;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String name){
        super( name + " is out of stock");
    }

}
