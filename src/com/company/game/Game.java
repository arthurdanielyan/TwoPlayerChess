package com.company.game;

import com.company.core.Position;

import static com.company.core.BoardLetters.*;


public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {

        board.resetBoard();
        board.render();

        board.getFigureByPosition(new Position(D, 2)).move(new Position(D, 4));
        board.getFigureByPosition(new Position(E, 7)).move(new Position(E, 5));
        board.getFigureByPosition(new Position(E, 2)).move(new Position(E, 3));
        board.getFigureByPosition(new Position(E, 5)).move(new Position(E, 4));
        board.getFigureByPosition(new Position(F, 2)).move(new Position(F, 4));
        board.getFigureByPosition(new Position(E, 4)).move(new Position(F, 3));

    }
}
