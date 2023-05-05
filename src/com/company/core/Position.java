package com.company.core;

import com.company.core.exceptions.IllegalSquareException;

import java.util.Objects;

import static com.company.core.Extensions.isLegalSquare;

public class Position {

    public int y; /** letters on the board */
    public int x; /** numbers on the board */

    public Position(int x, int y) {
        if(!isLegalSquare(x) || !isLegalSquare(y)) {
            throw new IllegalSquareException(new Position(x, y));
        }

        this.x = x;
        this.y = y;
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
}
