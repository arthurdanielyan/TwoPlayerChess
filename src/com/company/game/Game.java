package com.company.game;

public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {
        board.resetBoard();
        moveReader.requestMove();
    }
}
