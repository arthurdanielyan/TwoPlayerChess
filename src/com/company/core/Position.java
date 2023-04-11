package com.company.core;

import java.util.Objects;

public class Position {

    public int y; /** letters on the board */
    public int x; /** numbers on the board */

    public Position(int x, int y) {
        if(x < 1 || x > 8) {
            throw new IllegalArgumentException("move x must be in range of [1, 8]");
        } else if(y > 8 || y < 1) {
            throw new IllegalArgumentException("move y must be in range of [1, 8]");
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
