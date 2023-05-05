package com.company.core.exceptions;

import com.company.core.Position;

public class IllegalSquareException extends IndexOutOfBoundsException {

    private Position position;

    public IllegalSquareException(Position position) {
        this.position = position;
    }

    @Override
    public String getMessage() {
        boolean illegalX = false;
        boolean illegalY = false;
        if(position.x > 8 || position.x < 1) {
            illegalX = true;
        }
        if(position.y > 8 || position.y < 1) {
            illegalY = true;
        }
        if(illegalX && illegalY) {
            return "x and y of Position must be in range of [0, 8]";
        }
        if(illegalX) {
            return "x of Position must be in range of [0, 8]";
        }
        return "y of Position must be in range of [0, 8]";
    }
}
