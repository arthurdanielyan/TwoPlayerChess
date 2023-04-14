package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figur_impls.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static List<Figure> board = new ArrayList<>();

    static {
        Figure figure = new Pawn(true);
        figure.position = new Position(BoardLetters.F, 7);
        Figure queen = new Queen(true);
        queen.position = new Position(BoardLetters.D, 1);


        King niggaKing = new King(false);
        niggaKing.position = new Position(BoardLetters.E, 8);

        board.add(figure);
        board.add(niggaKing);
        board.add(queen);
        System.out.println(niggaKing.possibleMoves());
    }
}
