package com.company.core.exceptions;

import com.company.core.Position;

public class OccupiedSquareException extends Exception {

    private final Position position;

    public OccupiedSquareException(Position position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "Square " + position.toString() + " is occupied";
    }
}