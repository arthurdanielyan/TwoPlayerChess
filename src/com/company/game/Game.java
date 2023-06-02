package com.company.game;

import com.company.core.Position;

import static com.company.core.BoardLetters.*;


public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {

        board.resetBoard();
        board.render();

        board.getFigureByPosition(new Position(E, 2)).move(new Position(E, 4));
        board.getFigureByPosition(new Position(D, 7)).move(new Position(D, 6));
        board.getFigureByPosition(new Position(E, 4)).move(new Position(E, 5));
        board.getFigureByPosition(new Position(D, 6)).move(new Position(D, 5));
        board.getFigureByPosition(new Position(E, 5)).move(new Position(D, 6));

    }
}
