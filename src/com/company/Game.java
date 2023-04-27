package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquare;
import com.company.figures.Figure;
import com.company.figures.figur_impls.*;

public class Game {

    public static final Board board = new Board();

    public static void start() {
        Figure figure = new King(new Position(BoardLetters.E, 3), true);
        Figure obstacle1 = new Bishop(new Position(BoardLetters.H, 6), true);
        Figure obstacle2 = new Pawn(new Position(BoardLetters.E, 6), true);
        Figure obstacle3 = new Queen(new Position(BoardLetters.F, 2), false);
        Figure obstacle4 = new Knight(new Position(BoardLetters.A, 7), true);


        try {
            board.addFigure(figure);
            board.addFigure(obstacle1);
            board.addFigure(obstacle2);
            board.addFigure(obstacle3);
            board.addFigure(obstacle4);
        } catch (OccupiedSquare os) {
            System.out.println(os.getMessage());
        }

        System.out.println(Game.board.getFigureByPosition(new Position(BoardLetters.D, 3)));
        System.out.println(figure.position);
        System.out.println(figure.possibleMoves());
    }

}
