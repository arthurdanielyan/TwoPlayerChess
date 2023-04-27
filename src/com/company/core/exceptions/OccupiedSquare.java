package com.company.core.exceptions;

import com.company.core.Position;

public class OccupiedSquare extends Exception {

    private Position position;

    public OccupiedSquare(Position position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "Square " + position.toString() + " is Occupied";
    }
}
