package com.company.game;


import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.figure_impls.King;
import com.company.figures.figure_impls.Queen;

import static com.company.core.BoardLetters.A;
import static com.company.core.BoardLetters.F;

public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {


        try {



            board.addFigure(new King(new Position(A, 8), true));
            board.addFigure(new Queen(new Position(F, 7), true));
            board.addFigure(new Queen(new Position(F, 3), true));

            board.addFigure(new King(new Position(A, 1), false));


        } catch (OccupiedSquareException ignore){}
        moveReader.requestMove();
    }
}
