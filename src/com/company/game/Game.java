package com.company.game;


import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.figure_impls.King;
import com.company.figures.figure_impls.Knight;
import com.company.figures.figure_impls.Pawn;

import static com.company.core.BoardLetters.*;

public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {


//        try {
//            board.addFigure(new King(new Position(A, 8), true));
//            board.addFigure(new Pawn(new Position(G, 6), true));
//            board.addFigure(new Knight(new Position(H, 8), false));
//
//            board.addFigure(new King(new Position(A, 1), false));
//
//        } catch (OccupiedSquareException ignore){}
        board.resetBoard();
        moveReader.requestMove();
    }
}
