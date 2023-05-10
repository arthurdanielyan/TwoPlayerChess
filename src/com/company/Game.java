package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.Figure;
import com.company.figures.figure_impls.*;

public class Game {

    public static final Board board = new Board();

    public static void start() throws OccupiedSquareException {
        Figure figure = new Queen(new Position(BoardLetters.D, 5),true);

        board.addFigure(new Queen(new Position(BoardLetters.D, 7), false));
        board.addFigure(new King(new Position(BoardLetters.D, 2), true));
        board.addFigure(figure);


        System.out.println(figure.possibleMoves());
    }

}
