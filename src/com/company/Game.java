package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.Figure;
import com.company.figures.figure_impls.*;

public class Game {

    public static final Board board = new Board();

    public static void start() throws OccupiedSquareException {
        Figure figure = new Pawn(new Position(BoardLetters.E, 4), false);

        board.addFigure(new Bishop(new Position(BoardLetters.D, 3), true));
        board.addFigure(new Knight(new Position(BoardLetters.F, 3), true));
        board.addFigure(new Queen(new Position(BoardLetters.E, 1), true));
        board.addFigure(new King(new Position(BoardLetters.E, 6), false));
        board.addFigure(figure);


        System.out.println(figure.possibleMoves());
    }

}
