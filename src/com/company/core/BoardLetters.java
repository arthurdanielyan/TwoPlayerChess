package com.company.core;

/** Numeric representation of letters on board */
public class BoardLetters {

    public static final int A = 1;
    public static final int B = 2;
    public static final int C = 3;
    public static final int D = 4;
    public static final int E = 5;
    public static final int F = 6;
    public static final int G = 7;
    public static final int H = 8;

    public static int fileNumber(char y) {
        int file;
        switch (y) {
            case 'a' -> file = A;
            case 'b' -> file = B;
            case 'c' -> file = C;
            case 'd' -> file = D;
            case 'e' -> file = E;
            case 'f' -> file = F;
            case 'g' -> file = G;
            case 'h' -> file = H;
            default -> throw new IllegalArgumentException();
        }
        return file;
    }
}
