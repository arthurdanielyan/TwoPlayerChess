package com.company.core;

/** Numeric representation of letters on board */
public enum BoardLetters {
    A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8);

    public final int num;

    BoardLetters(int colNum) {
        this.num = colNum;
    }
}
