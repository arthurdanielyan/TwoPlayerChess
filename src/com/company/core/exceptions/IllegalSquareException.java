package com.company.core.exceptions;


public class IllegalSquareException extends IndexOutOfBoundsException {

    private final int x;
    private final int y;


    public IllegalSquareException(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String getMessage() {
        boolean illegalX = false;
        boolean illegalY = false;
        if(x > 8 || x < 1) {
            illegalX = true;
        }
        if(y > 8 || y < 1) {
            illegalY = true;
        }
        if(illegalX && illegalY) {
            return "x and y of Position must be in range of [0, 8], but is " + x + ", " + y;
        }
        if(illegalX) {
            return "x of Position must be in range of [0, 8], but is " + x;
        }
        return "y of Position must be in range of [0, 8], but is " + y;
    }
}
