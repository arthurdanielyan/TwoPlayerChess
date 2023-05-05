package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.Figure;
import com.company.figures.figure_impls.Bishop;
import com.company.figures.figure_impls.King;
import com.company.figures.figure_impls.Queen;

public class Game {

    public static final Board board = new Board();

    public static void start() throws OccupiedSquareException {
        Figure figure = new Bishop(new Position(BoardLetters.C, 5), false);
        System.out.println(figure);

        board.addFigure(new Queen(new Position(BoardLetters.A, 7), true));
        board.addFigure(new King(new Position(BoardLetters.E, 3), false));
        board.addFigure(figure);


        System.out.println(figure.possibleMoves());
    }

}
