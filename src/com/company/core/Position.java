package com.company.core;

import com.company.core.exceptions.IllegalSquareException;

import java.util.Objects;

public class Position {

    public int y; /** numbers on the board */
    public int x; /** letters on the board */

    public Position(int x, int y) {
        if(isIllegalSquare(x) || isIllegalSquare(y)) {
            throw new IllegalSquareException(x, y);
        }

        this.x = x;
        this.y = y;
    }

    public boolean isLightSquare() {
        return (x+y)%2 != 0;
    }

    @Override
    public String toString() {
        String letter;
        switch (x) {
            case 1 -> letter = "A";
            case 2 -> letter = "B";
            case 3 -> letter = "C";
            case 4 -> letter = "D";
            case 5 -> letter = "E";
            case 6 -> letter = "F";
            case 7 -> letter = "G";
            case 8 -> letter = "H";
            default -> letter = "wtf";
        }

        return letter + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return y == position.y && x == position.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    public static Position fromString(String square) throws IllegalArgumentException {
        if(square.length() > 2) throw new IllegalArgumentException("Provided string " + square + " doesn't represent any square");
        int x;
        switch(square.charAt(0)) {
            case 'a' -> x = 1;
            case 'b' -> x = 2;
            case 'c' -> x = 3;
            case 'd' -> x = 4;
            case 'e' -> x = 5;
            case 'f' -> x = 6;
            case 'g' -> x = 7;
            case 'h' -> x = 8;
            default -> throw new IllegalArgumentException("Provided string " + square + " doesn't represent any square");
        }


        return new Position(x, Character.getNumericValue(square.charAt(1)));
    }

    private static boolean isIllegalSquare(int n) {
        return (n < 1 || n > 8);
    }
}
