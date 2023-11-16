package com.kolot.jubilantwaddle.validation;

public class IsAlreadyExistException extends RuntimeException {

    public IsAlreadyExistException(String message) {
        super(message);
    }
}
