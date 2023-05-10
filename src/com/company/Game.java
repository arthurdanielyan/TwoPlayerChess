package com.company;


import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.core.exceptions.OccupiedSquareException;
import com.company.figures.Figure;
import com.company.figures.figure_impls.Bishop;
import com.company.figures.figure_impls.King;
import com.company.figures.figure_impls.Queen;

import java.util.Scanner;

public class Game {

    public static final Board board = new Board();
    private static final MoveReader moveReader = new MoveReader(board);

    public static void start() {
//        board.resetBoard();
//        board.render();
//        Scanner input = new Scanner(System.in);
//        moveReader.readMove(input.nextLine());
//        input.close();

//        System.out.println(board.getKing(true).possibleMoves());

        Figure figure = new King(new Position(BoardLetters.G, 7), false);

        try {
            board.addFigure(figure);
            board.addFigure(new King(new Position(BoardLetters.F, 2), true));
            board.addFigure(new Bishop(new Position(BoardLetters.A, 7), false));
            board.addFigure(new Queen(new Position(BoardLetters.D, 4), true));
        } catch (OccupiedSquareException ignore) {}

        System.out.println(figure.possibleMoves());
    }

}
