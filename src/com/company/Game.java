package com.company;

import com.company.core.BoardLetters;
import com.company.core.Position;
import com.company.figures.Figure;
import com.company.figures.figur_impls.King;

import java.util.ArrayList;
import java.util.List;

public class Game {

//    public static int[][] board = {
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0}
//    };

    public static List<Figure> board = new ArrayList<>();
    static {
        King whiteKing = new King(true);
        whiteKing.position = new Position(BoardLetters.E.num, 1);
        board.add(whiteKing);

        King blackKing = new King(true);
        blackKing.position = new Position(BoardLetters.E.num, 2);
        board.add(blackKing);
    }
}
