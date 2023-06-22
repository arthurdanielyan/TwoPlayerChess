package com.company.game;


import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.figure_impls.Bishop;
import com.company.figures.figure_impls.King;
import com.company.figures.figure_impls.Rook;

import static com.company.core.BoardLetters.*;

public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {
//        board.resetBoard();
//        moveReader.requestMove();

        try {
            board.addFigure(new King(new Position(A, 1), true));
            board.addFigure(new King(new Position(F, 2), false));
            board.addFigure(new Bishop(new Position(B, 6), true));
            board.addFigure(new Rook(new Position(E, 6), false));

            System.out.println(board.getFigureByPosition(new Position(E, 6)).possibleMoves());

        } catch (OccupiedSquareException ose) {
            ose.printStackTrace();
        }

    }
}
