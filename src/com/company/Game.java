package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.Figure;
import com.company.figures.figure_impls.Bishop;
import com.company.figures.figure_impls.King;
import com.company.figures.figure_impls.Queen;
import com.company.figures.figure_impls.Rook;


public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() throws OccupiedSquareException {
        Figure figure = new Bishop(new Position(BoardLetters.E, 8), false);

        board.addFigure(new Queen(new Position(BoardLetters.A, 6), true));
        board.addFigure(new King(new Position(BoardLetters.H, 8), true));
        board.addFigure(new King(new Position(BoardLetters.E, 2), false));
        board.addFigure(new Rook(new Position(BoardLetters.A, 8), false));


        System.out.println(figure.possibleMoves());

//        board.render();
//        moveReader.requestMove();
    }

}
