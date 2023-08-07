package com.company.core.exceptions;

public class IllegalMoveException extends RuntimeException {

    public IllegalMoveException(String massage) {
        super(massage);
    }
}
